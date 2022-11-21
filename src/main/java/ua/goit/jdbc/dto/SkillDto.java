package ua.goit.jdbc.dto;

public class SkillDto {
    Integer skillId;
    String language;
    String level;

    public SkillDto() {
    }

    public SkillDto(Integer skillId, String language, String level) {
        this.skillId = skillId;
        this.language = language;
        this.level = level;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDto skillDto = (SkillDto) o;

        if (skillId != null ? !skillId.equals(skillDto.skillId) : skillDto.skillId != null) return false;
        if (language != null ? !language.equals(skillDto.language) : skillDto.language != null) return false;
        return level != null ? level.equals(skillDto.level) : skillDto.level == null;
    }

    @Override
    public int hashCode() {
        int result = skillId != null ? skillId.hashCode() : 0;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SkillDao{");
        sb.append("skillId=").append(skillId);
        sb.append(", language='").append(language).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
