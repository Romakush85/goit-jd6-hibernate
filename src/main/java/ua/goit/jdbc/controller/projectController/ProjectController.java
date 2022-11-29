package ua.goit.jdbc.controller.projectController;
import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.config.PropertiesConfig;
import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.dto.ProjectDto;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.service.ProjectService;
import ua.goit.jdbc.service.converter.ProjectConverter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/projects")
public class ProjectController extends HttpServlet {
    private ProjectService projectService;
    private ProjectRepository projectRepository;

    @Override
    public void init() throws ServletException {
        HibernateProvider provider = new HibernateProvider();
        projectRepository = new ProjectRepository(provider);
        ProjectConverter projectConverter  = new ProjectConverter();
        projectService = new ProjectService(projectRepository, projectConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "find":
                findProjectById(req, resp);
                break;
            case "findAll":
                findAllProjects(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "create":
                createProject(req, resp);
                break;
            case "update":
                updateProject(req, resp);
                break;
            case "delete":
                deleteProject(req, resp);
                break;
        }
    }

    protected void findProjectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer projectId =Integer.parseInt(req.getParameter("projectId"));
        Optional<ProjectDto> optional = projectService.findById(projectId);
        req.setAttribute("project", optional.orElseGet(() -> {return null;}));
        req.getRequestDispatcher("/WEB-INF/jsp/project/findProject.jsp").forward(req, resp);

    }

    protected void findAllProjects(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectDto> projects = projectService.findAll();
        System.out.println(projects);
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/jsp/project/findAllProjects.jsp").forward(req, resp);
    }

    protected void createProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(req.getParameter("name"));
        projectDto.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        projectDto.setCost(Integer.parseInt(req.getParameter("cost")));
        projectService.save(projectDto);
        req.getRequestDispatcher("/WEB-INF/jsp/project/savedProject.jsp").forward(req, resp);
    }

    private void updateProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(Integer.parseInt(req.getParameter("projectId")));
        projectDto.setName(req.getParameter("name"));
        projectDto.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        projectDto.setCost(Integer.parseInt(req.getParameter("cost")));
        if(projectService.update(projectDto)){
            req.setAttribute("result", "Project updated!");
        } else {
            req.setAttribute("result", "Project not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/updateProjectForm.jsp").forward(req, resp);
    }

    private void deleteProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer projectId = Integer.parseInt(req.getParameter("projectId"));
        if(projectService.findById(projectId).isPresent()){
            projectRepository.deleteById(projectId);
            req.setAttribute("result", "Project  deleted!");
        } else {
            req.setAttribute("result", "Project not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/project/deleteProjectForm.jsp").forward(req, resp);
    }
}
