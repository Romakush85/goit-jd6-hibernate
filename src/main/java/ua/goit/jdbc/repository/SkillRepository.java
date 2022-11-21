package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.SkillDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository implements Repository<SkillDao>{
    private final DatabaseManagerConnector connector;

    private final static String INSERT = "INSERT INTO skills ( language, level) " +
            "VALUES (?, ?)";
    private final static String UPDATE = "UPDATE skills SET  language = ?, " +
            "level = ? WHERE skill_id = ?";
    private final static String SELECT_BY_ID = "SELECT skill_id, language, level FROM skills WHERE " +
            "skill_id = ?";
    private final static String SELECT_ALL = "SELECT skill_id, language, level FROM skills";
    private final static String DELETE_BY_ID = "DELETE FROM skills WHERE skill_id = ?";

    public SkillRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public SkillDao save(SkillDao skill) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, skill.getLanguage());
            statement.setString(2, skill.getLevel());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public void update(SkillDao skill) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, skill.getLanguage());
            statement.setString(2, skill.getLevel());
            statement.setInt(3, skill.getSkillId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't update skill");
        }
    }

    @Override
    public Optional<SkillDao> findById(Integer id)  {
        SkillDao skillDao = null;
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    skillDao = convert(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return Optional.ofNullable(skillDao);
    }

    @Override
    public List<SkillDao> findAll() {
        List<SkillDao> skills = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    skills.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get skills");
        }
        return skills;
    }

    public void deleteById(Integer id) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't delete skill");
        }
    }

    private SkillDao convert(ResultSet resultSet) throws SQLException {
        SkillDao skillDao = new SkillDao();
        skillDao.setSkillId(resultSet.getInt("skill_id"));
        skillDao.setLanguage(resultSet.getString("language"));
        skillDao.setLevel(resultSet.getString("level"));
        return skillDao;
    }
}
