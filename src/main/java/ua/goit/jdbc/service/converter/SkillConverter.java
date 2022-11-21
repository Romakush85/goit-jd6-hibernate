package ua.goit.jdbc.service.converter;

import ua.goit.jdbc.dao.SkillDao;
import ua.goit.jdbc.dto.SkillDto;

public class SkillConverter implements Converter<SkillDto, SkillDao>{
    @Override
    public SkillDto from(SkillDao entity) {
        SkillDto skillDto = new SkillDto();
        skillDto.setSkillId(entity.getSkillId());
        skillDto.setLanguage(entity.getLanguage());
        skillDto.setLevel(entity.getLevel());
        return skillDto;
    }

    @Override
    public SkillDao to(SkillDto entity) {
        SkillDao skillDao = new SkillDao();
        skillDao.setSkillId(entity.getSkillId());
        skillDao.setLanguage(entity.getLanguage());
        skillDao.setLevel(entity.getLevel());
        return skillDao;
    }
}
