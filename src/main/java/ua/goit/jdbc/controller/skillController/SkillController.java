package ua.goit.jdbc.controller.skillController;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.dto.SkillDto;
import ua.goit.jdbc.repository.SkillRepository;
import ua.goit.jdbc.service.SkillService;
import ua.goit.jdbc.service.converter.SkillConverter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/skills")
public class SkillController extends HttpServlet {
    private SkillRepository skillRepository;
    private SkillService skillService;

    @Override
    public void init() throws ServletException {
        String dbUsername = System.getenv("dbUsername");
        String dbPassword = System.getenv("dbPassword");
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        Properties properties = propertiesConfig.loadProperties("application.properties");
        DatabaseManagerConnector dbManager = new DatabaseManagerConnector(properties, dbUsername, dbPassword);
        skillRepository = new SkillRepository(dbManager);
       SkillConverter skillConverter = new SkillConverter();
       skillService = new SkillService(skillRepository, skillConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "find":
                findSkillById(req, resp);
                break;
            case "findAll":
                findAllSkills(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "create":
                createSkill(req, resp);
                break;
            case "update":
                updateSkill(req, resp);
                break;
            case "delete":
                deleteSkill(req, resp);
                break;
        }
    }

    protected void findSkillById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer skillId =Integer.parseInt(req.getParameter("skillId"));
        Optional<SkillDto> optional = skillService.findById(skillId);
        req.setAttribute("skill", optional.orElseGet(() -> {return null;}));
        req.getRequestDispatcher("/WEB-INF/jsp/skill/findSkill.jsp").forward(req, resp);
    }

    protected void findAllSkills(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SkillDto> skills = skillService.findAll();
        System.out.println(skills);
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/WEB-INF/jsp/skill/findAllSkills.jsp").forward(req, resp);
    }

    protected void createSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDto skillDto = new SkillDto();
        skillDto.setLanguage(req.getParameter("language"));
        skillDto.setLevel(req.getParameter("level"));
        skillService.save(skillDto);
        req.getRequestDispatcher("/WEB-INF/jsp/skill/savedSkill.jsp").forward(req, resp);
    }

    private void updateSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDto skillDto = new SkillDto();
        skillDto.setSkillId(Integer.parseInt(req.getParameter("skillId")));
        skillDto.setLanguage(req.getParameter("language"));
        skillDto.setLevel(req.getParameter("level"));
        if(skillService.update(skillDto)){
            req.setAttribute("result", "Skill updated!");
        } else {
            req.setAttribute("result", "Skill not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/skill/updateSkillForm.jsp").forward(req, resp);
    }

    private void deleteSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer skillId = Integer.parseInt(req.getParameter("skillId"));
        if(skillService.findById(skillId).isPresent()){
            skillRepository.deleteById(skillId);
            req.setAttribute("result", "Skill  deleted!");
        } else {
            req.setAttribute("result", "Skill not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/skill/deleteSkillForm.jsp").forward(req, resp);
    }
}
