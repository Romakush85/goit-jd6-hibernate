package ua.goit.jdbc.controller.customerController;


import ua.goit.jdbc.config.HibernateProvider;


import ua.goit.jdbc.dto.CustomerDto;

import ua.goit.jdbc.repository.CustomerRepository;

import ua.goit.jdbc.service.CustomerService;

import ua.goit.jdbc.service.converter.CustomerConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet("/customers")
public class CustomerController extends HttpServlet {
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @Override
    public void init()  {
        HibernateProvider provider = new HibernateProvider();
        customerRepository = new CustomerRepository(provider);
        CustomerConverter customerConverter = new CustomerConverter();
        customerService = new CustomerService(customerRepository, customerConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "find":
                findCustomerById(req, resp);
                break;
            case "findAll":
                findAllCustomers(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        switch (command) {
            case "create":
                createCustomer(req, resp);
                break;
            case "update":
                updateCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req, resp);
                break;
        }
    }

    protected void findCustomerById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer customerId =Integer.parseInt(req.getParameter("customerId"));
        Optional<CustomerDto> optional = customerService.findById(customerId);
        req.setAttribute("customer", optional.orElseGet(() -> {return null;}));
        req.getRequestDispatcher("/WEB-INF/jsp/customer/findCustomer.jsp").forward(req, resp);
    }

    protected void findAllCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerDto> customers = customerService.findAll();
        System.out.println(customers);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/jsp/customer/findAllCustomers.jsp").forward(req, resp);
    }

    protected void createCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto  = new CustomerDto();
        customerDto.setName(req.getParameter("name"));
        customerDto.setCountry(req.getParameter("country"));
        customerDto.setContactPerson(req.getParameter("contactPerson"));
        customerDto.setEmail(req.getParameter("email"));
        customerService.save(customerDto);
        req.getRequestDispatcher("/WEB-INF/jsp/customer/savedCustomer.jsp").forward(req, resp);
    }

    private  void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(Integer.parseInt(req.getParameter("customerId")));
        customerDto.setName(req.getParameter("name"));
        customerDto.setCountry(req.getParameter("country"));
        customerDto.setContactPerson(req.getParameter("contactPerson"));
        customerDto.setEmail(req.getParameter("email"));
        if(customerService.update(customerDto)){
            req.setAttribute("result", "Customer updated!");
        } else {
            req.setAttribute("result", "Customer not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/customer/updateCustomerForm.jsp").forward(req, resp);
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer customerId = Integer.parseInt(req.getParameter("customerId"));
        if(customerService.findById(customerId).isPresent()){
            customerRepository.deleteById(customerId);
            req.setAttribute("result", "Customer  deleted!");
        } else {
            req.setAttribute("result", "Customer not found!");
        }
        req.getRequestDispatcher("/WEB-INF/jsp/customer/deleteCustomerForm.jsp").forward(req, resp);
    }
}
