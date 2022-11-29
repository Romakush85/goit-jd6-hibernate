package ua.goit.jdbc.controller.developerController;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.DeveloperService;
import ua.goit.jdbc.service.converter.DeveloperConverter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/developers")
public class DeveloperController extends HttpServlet {
    private DeveloperService developerService;
    private DeveloperRepository developerRepository;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        developerRepository = new DeveloperRepository(provider);
        DeveloperConverter developerConverter = new DeveloperConverter();
        developerService = new DeveloperService(developerRepository, developerConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "find":
                findDeveloperById(req, resp);
                break;
            case "findAll":
                findAllDevelopers(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "create":
                createDeveloper(req, resp);
                break;
            case "update":
                updateDeveloper(req, resp);
                break;
            case "delete":
                deleteDeveloper(req, resp);
                break;
        }
    }

    protected void findDeveloperById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer devId =Integer.parseInt(req.getParameter("devId"));
        Optional<DeveloperDto> optional = developerService.findById(devId);
        req.setAttribute("developer", optional.orElseGet(() -> {return null;}));
        req.getRequestDispatcher("/WEB-INF/jsp/developer/findDeveloper.jsp").forward(req, resp);
    }

    protected void findAllDevelopers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDto> developers = developerService.findAll();
        System.out.println(developers);
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/findAllDevelopers.jsp").forward(req, resp);
    }

    protected void createDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String gender = req.getParameter("gender");
        Integer salary = Integer.parseInt(req.getParameter("salary"));
        DeveloperDto developer = new DeveloperDto();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setBirthDate(date);
        developer.setGender(gender);
        developer.setSalary(salary);
        developerService.save(developer);
        req.getRequestDispatcher("/WEB-INF/jsp/developer/savedDeveloper.jsp").forward(req, resp);
    }

    private void updateDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("birthDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DeveloperDto developer = new DeveloperDto();
        developer.setDevId(Integer.parseInt(req.getParameter("devId")));
        developer.setFirstName(req.getParameter("firstName"));
        developer.setLastName(req.getParameter("lastName"));
        developer.setBirthDate(date);
        developer.setGender(req.getParameter("gender"));
        developer.setSalary(Integer.parseInt(req.getParameter("salary")));
        if(developerService.update(developer)){
            req.setAttribute("result", "Developer updated!");
        } else {
            req.setAttribute("result", "Developer not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/developer/updateDeveloperForm.jsp").forward(req, resp);
    }

    private void deleteDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer devId = Integer.parseInt(req.getParameter("devId"));
        if(developerService.findById(devId).isPresent()){
            developerRepository.deleteById(devId);
            req.setAttribute("result", "Developer  deleted!");
        } else {
            req.setAttribute("result", "Developer not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/developer/deleteDeveloperForm.jsp").forward(req, resp);
    }


}
