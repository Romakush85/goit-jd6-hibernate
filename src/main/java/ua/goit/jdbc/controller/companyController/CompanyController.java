package ua.goit.jdbc.controller.companyController;

import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.repository.CompanyRepository;
import ua.goit.jdbc.service.CompanyService;
import ua.goit.jdbc.service.converter.CompanyConverter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/companies")
public class CompanyController extends HttpServlet {
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    @Override
    public void init() {
        HibernateProvider provider = new HibernateProvider();
        companyRepository = new CompanyRepository(provider);
        CompanyConverter companyConverter = new CompanyConverter();
        companyService = new CompanyService(companyRepository, companyConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "find":
                findCompanyById(req, resp);
                break;
            case "findAll":
                findAllCompanies(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "create":
                createCompany(req, resp);
                break;
            case "update":
                updateCompany(req, resp);
                break;
            case "delete":
                deleteCompany(req, resp);
                break;
        }
    }

    protected void findCompanyById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer companyId =Integer.parseInt(req.getParameter("companyId"));
        Optional<CompanyDto> optional = companyService.findById(companyId);
        req.setAttribute("company", optional.orElseGet(() -> {return null;}));
        req.getRequestDispatcher("/WEB-INF/jsp/company/findCompany.jsp").forward(req, resp);
    }

    protected void findAllCompanies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDto> companies = companyService.findAll();
        System.out.println(companies);
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/WEB-INF/jsp/company/findAllCompanies.jsp").forward(req, resp);
    }

    protected void createCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName(req.getParameter("name"));
        companyDto.setCountry(req.getParameter("country"));
        companyDto.setContactPerson(req.getParameter("contactPerson"));
        companyDto.setEmail(req.getParameter("email"));
        companyService.save(companyDto);
        req.getRequestDispatcher("/WEB-INF/jsp/company/savedCompany.jsp").forward(req, resp);
    }

    private  void updateCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyId(Integer.parseInt(req.getParameter("companyId")));
        companyDto.setName(req.getParameter("name"));
        companyDto.setCountry(req.getParameter("country"));
        companyDto.setContactPerson(req.getParameter("contactPerson"));
        companyDto.setEmail(req.getParameter("email"));
        if(companyService.update(companyDto)){
            req.setAttribute("result", "Company updated!");
        } else {
            req.setAttribute("result", "Company not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/company/updateCompanyForm.jsp").forward(req, resp);
    }

    private void deleteCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer companyId = Integer.parseInt(req.getParameter("companyId"));
        if(companyService.findById(companyId).isPresent()){
            companyRepository.deleteById(companyId);
            req.setAttribute("result", "Company  deleted!");
        } else {
            req.setAttribute("result", "Company not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/company/deleteCompanyForm.jsp").forward(req, resp);
    }
}
