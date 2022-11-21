package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.dao.ProjectDao;
import ua.goit.jdbc.dto.ProjectDto;

public class ProjectConverter implements Converter<ProjectDto, ProjectDao>{
    @Override
    public ProjectDto from(ProjectDao entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(entity.getProjectId());
        projectDto.setName(entity.getName());
        projectDto.setCustomerId(entity.getCustomerId());
        projectDto.setCost(entity.getCost());
        return  projectDto;
    }

    @Override
    public ProjectDao to(ProjectDto entity) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setProjectId(entity.getProjectId());
        projectDao.setName(entity.getName());
        projectDao.setCustomerId(entity.getCustomerId());
        projectDao.setCost(entity.getCost());
        return  projectDao;
    }
}
