package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.ProjectDao;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.dto.ProjectDto;
import ua.goit.jdbc.repository.ProjectRepository;
import ua.goit.jdbc.service.converter.ProjectConverter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;

    public ProjectService(ProjectRepository projectRepository, ProjectConverter projectConverter) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
    }

    public ProjectDto save(ProjectDto projectDto){
        ProjectDao projectDao  = projectRepository.save(projectConverter.to(projectDto));
        return projectConverter.from(projectDao);
    }

    public boolean update(ProjectDto projectDto) {
        if(projectRepository.findById(projectDto.getProjectId()).isPresent()) {
            ProjectDao projectToUpdate = projectConverter.to(projectDto);
            projectRepository.update(projectToUpdate);
            ProjectDto updatedProject = findById(projectDto.getProjectId()).orElseGet(() ->  null);
            if(projectDto.equals(updatedProject)) {
                return true;
            } else {return false;}
        }
        return false;
    }

    public Optional<ProjectDto> findById(Integer id)  {
        Optional<ProjectDao> projectDao = projectRepository.findById(id);
        return projectDao.map(dao -> projectConverter.from(dao));
    }

    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(dao -> projectConverter.from(dao))
                .collect(Collectors.toList());
    }
}
