package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.CompanyDao;
import ua.goit.jdbc.dto.CompanyDto;
import ua.goit.jdbc.repository.CompanyRepository;
import ua.goit.jdbc.service.converter.CompanyConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;

    public CompanyService(CompanyRepository companyRepository, CompanyConverter companyConverter) {
        this.companyRepository = companyRepository;
        this.companyConverter = companyConverter;
    }


    public CompanyDto save(CompanyDto companyDto) {
        CompanyDao companyDao = companyRepository.save(companyConverter.to(companyDto));
        return  companyConverter.from(companyDao);
    }

    public boolean update(CompanyDto companyDto) {
        if(companyRepository.findById(companyDto.getCompanyId()).isPresent()){
            CompanyDao companyToUpdate = companyConverter.to(companyDto);
            companyRepository.update(companyToUpdate);
            CompanyDto updatedCompany = findById(companyDto.getCompanyId()).orElseGet(null);
            if(companyDto.equals(updatedCompany)) {
                return true;
            } else { return false;}
        }
        return false;
    }

    public Optional<CompanyDto> findById(Integer id) {
        Optional<CompanyDao> companyDao = companyRepository.findById(id);
        return companyDao.map(dao -> companyConverter.from(dao));
    }

    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream().map(dao -> companyConverter.from(dao))
                .collect(Collectors.toList());
    }
}
