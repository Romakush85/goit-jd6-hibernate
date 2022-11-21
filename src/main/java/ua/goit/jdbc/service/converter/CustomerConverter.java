package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.dao.CustomerDao;
import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.dto.CustomerDto;

public class CustomerConverter implements Converter<CustomerDto, CustomerDao>{
    @Override
    public CustomerDto from(CustomerDao entity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(entity.getCustomerId());
        customerDto.setName(entity.getName());
        customerDto.setCountry(entity.getCountry());
        customerDto.setContactPerson(entity.getContactPerson());
        customerDto.setEmail(entity.getEmail());
        return customerDto;
    }

    @Override
    public CustomerDao to(CustomerDto entity) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setCustomerId(entity.getCustomerId());
        customerDao.setName(entity.getName());
        customerDao.setCountry(entity.getCountry());
        customerDao.setContactPerson(entity.getContactPerson());
        customerDao.setEmail(entity.getEmail());
        return customerDao;
    }
}
