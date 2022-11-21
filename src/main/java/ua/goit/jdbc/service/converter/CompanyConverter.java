package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.dao.CompanyDao;

public class CompanyConverter implements Converter<CompanyDto, CompanyDao>{
    @Override
    public CompanyDto from(CompanyDao entity) {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyId(entity.getCompanyId());
        companyDto.setName(entity.getName());
        companyDto.setCountry(entity.getCountry());
        companyDto.setContactPerson(entity.getContactPerson());
        companyDto.setEmail(entity.getEmail());
        return companyDto;
    }

    @Override
    public CompanyDao to(CompanyDto entity) {
        CompanyDao companyDao = new CompanyDao();
        companyDao.setCompanyId(entity.getCompanyId());
        companyDao.setName(entity.getName());
        companyDao.setCountry(entity.getCountry());
        companyDao.setContactPerson(entity.getContactPerson());
        companyDao.setEmail(entity.getEmail());
        return companyDao;
    }
}
