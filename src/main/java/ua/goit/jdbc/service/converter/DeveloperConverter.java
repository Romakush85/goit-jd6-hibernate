package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.dto.DeveloperDto;

public class DeveloperConverter implements Converter<DeveloperDto, DeveloperDao>{

    @Override
    public DeveloperDto from(DeveloperDao entity) {
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setDevId(entity.getDevId());
        developerDto.setFirstName(entity.getFirstName());
        developerDto.setLastName(entity.getLastName());
        developerDto.setBirthDate(entity.getBirthDate());
        developerDto.setGender(entity.getGender());
        developerDto.setSalary(entity.getSalary());
        return developerDto;
    }

    @Override
    public DeveloperDao to(DeveloperDto entity) {
        DeveloperDao developerDao = new DeveloperDao();
        developerDao.setDevId(entity.getDevId());
        developerDao.setFirstName(entity.getFirstName());
        developerDao.setLastName(entity.getLastName());
        developerDao.setBirthDate(entity.getBirthDate());
        developerDao.setGender(entity.getGender());
        developerDao.setSalary(entity.getSalary());
        return developerDao;
    }
}
