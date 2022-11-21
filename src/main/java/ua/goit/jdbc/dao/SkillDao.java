package ua.goit.jdbc.dao;

public class SkillDao {
    Integer skillId;
    String language;
    String level;

    public SkillDao() {
    }

    public SkillDao(Integer skillId, String language, String level) {
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("SkillDao{");
        sb.append("skillId=").append(skillId);
        sb.append(", language='").append(language).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
