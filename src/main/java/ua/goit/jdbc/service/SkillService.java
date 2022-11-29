package ua.goit.jdbc.service;

import ua.goit.jdbc.dao.SkillDao;
import ua.goit.jdbc.dto.SkillDto;
import ua.goit.jdbc.repository.SkillRepository;
import ua.goit.jdbc.service.converter.SkillConverter;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillConverter skillConverter;

    public SkillService(SkillRepository skillRepository, SkillConverter skillConverter) {
        this.skillRepository = skillRepository;
        this.skillConverter = skillConverter;
    }

    public SkillDto save(SkillDto skillDto) {
        SkillDao skillDao = skillRepository.save(skillConverter.to(skillDto));
        return skillConverter.from(skillDao);
    }

    public boolean update(SkillDto skillDto)  {
        if(skillRepository.findById(skillDto.getSkillId()).isPresent()) {
            SkillDao skillToUpdate = skillConverter.to(skillDto);
            skillRepository.update(skillToUpdate);
            SkillDto updatedSkill = findById(skillDto.getSkillId()).orElseGet(() ->  null);
            if(skillDto.equals(updatedSkill)) {
                return true;
            } else {return false;}
        }
        return false;
    }

    public Optional<SkillDto> findById(Integer id) {
        Optional<SkillDao> skillDao = skillRepository.findById(id);
        return skillDao.map(dao -> skillConverter.from(dao));
    }

    public List<SkillDto> findAll() {
        return skillRepository.findAll().stream().map(dao -> skillConverter.from(dao))
                .collect(Collectors.toList());
    }
}
