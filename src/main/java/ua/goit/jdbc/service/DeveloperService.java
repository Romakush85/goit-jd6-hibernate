package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.dto.DeveloperDto;
import ua.goit.jdbc.repository.DeveloperRepository;
import ua.goit.jdbc.service.converter.DeveloperConverter;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperConverter developerConverter;

    public DeveloperService(DeveloperRepository developerRepository, DeveloperConverter developerConverter) {
        this.developerConverter = developerConverter;
        this.developerRepository = developerRepository;
    }

    public DeveloperDto save(DeveloperDto developerDto){
        DeveloperDao developerDao = developerRepository.save(developerConverter.to(developerDto));
        return developerConverter.from(developerDao);
    }

    public boolean update(DeveloperDto developerDto) {
        if(developerRepository.findById(developerDto.getDevId()) != null) {
            DeveloperDao developerToUpdate = developerConverter.to(developerDto);
            developerRepository.update(developerToUpdate);
            DeveloperDto updatedDeveloper = findById(developerDto.getDevId()).orElseGet(() -> {return null;});
            if(developerDto.equals(updatedDeveloper)) {
                return true;
            } else {return false;}
        }
        return false;
    }

    public Optional<DeveloperDto> findById(Integer id)  {
        Optional<DeveloperDao> developerDao = developerRepository.findById(id);
        return developerDao.map(dao -> developerConverter.from(dao));
    }

    public List<DeveloperDto> findAllByProjectId(Integer id) {
        return developerRepository.findAllByProjectId(id).stream().map(dao -> developerConverter.from(dao))
                .collect(Collectors.toList());
    }

    public List<DeveloperDto> findAllJavaDevelopers() {
        return developerRepository.findAllJavaDevelopers().stream().map(dao -> developerConverter.from(dao))
                .collect(Collectors.toList());
    }

    public List<DeveloperDto> findAllMiddleDevelopers() {
        return developerRepository.findAllMiddleDevelopers().stream().map(dao -> developerConverter.from(dao))
                .collect(Collectors.toList());
    }

    public List<DeveloperDto> findAll() {
        return developerRepository.findAll().stream().map(dao -> developerConverter.from(dao))
                .collect(Collectors.toList());
    }


}

