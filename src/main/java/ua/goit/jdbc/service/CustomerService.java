package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.CustomerDao;
import ua.goit.jdbc.dto.CustomerDto;
import ua.goit.jdbc.repository.CustomerRepository;
import ua.goit.jdbc.service.converter.CustomerConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    public CustomerDto save(CustomerDto customerDto) {
        CustomerDao customerDao = customerRepository.save(customerConverter.to(customerDto));
        return  customerConverter.from(customerDao);
    }

    public boolean update(CustomerDto customerDto) {
        if(customerRepository.findById(customerDto.getCustomerId()) != null){
            CustomerDao customerToUpdate = customerConverter.to(customerDto);
            customerRepository.update(customerToUpdate);
            CustomerDto updatedCustomer = findById(customerDto.getCustomerId()).orElseGet(null);
            if(customerDto.equals(updatedCustomer)) {
                return true;
            } else { return false;}
        }
        return false;
    }

    public Optional<CustomerDto> findById(Integer id) {
        Optional<CustomerDao> customerDao = customerRepository.findById(id);
        return customerDao.map(dao -> customerConverter.from(dao));
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(dao -> customerConverter.from(dao))
                .collect(Collectors.toList());
    }
}
