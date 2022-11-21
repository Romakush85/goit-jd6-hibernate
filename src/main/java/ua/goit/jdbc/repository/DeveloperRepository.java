package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.dao.ProjectDao;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DeveloperRepository implements Repository<DeveloperDao>{

    private final DatabaseManagerConnector connector;

    private static final String INSERT = "INSERT INTO developers (first_name, last_name, birth_date, gender, salary)" +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE developers SET first_name = ?, last_name = ?, birth_date = ?, gender = ?, " +
            "salary = ?  WHERE dev_id = ?";

    private static final String SELECT_BY_ID = "SELECT dev_id, first_name, last_name, birth_date, gender, salary FROM "
        + "developers WHERE dev_id = ?";

    private static final String SELECT_ALL_BY_PROJECT_ID = "SELECT d.dev_id, d.first_name, d.last_name, d.birth_date," +
        " d.gender, d.salary FROM developers d INNER JOIN developers_projects dp ON dp.dev_id = d.dev_id" +
            " WHERE dp.project_id = ?";

    private static final String SELECT_ALL_WITH_MIDDLE_LEVEL = "SELECT DISTINCT d.dev_id, d.first_name, d.last_name, " +
        "d.birth_date, d.gender, d.salary FROM developers d " +
            "INNER JOIN developers_skills ds ON ds.dev_id = d.dev_id " +
            "INNER JOIN skills s ON s.skill_id = ds.skill_id " +
            "WHERE s.level = 'Middle'";
    private static final String SELECT_ALL_WITH_JAVA_LANGUAGE = "SELECT DISTINCT d.dev_id, d.first_name, d.last_name, " +
            "d.birth_date, d.gender, d.salary FROM developers d " +
            "INNER JOIN developers_skills ds ON ds.dev_id = d.dev_id " +
            "INNER JOIN skills s ON s.skill_id = ds.skill_id " +
            "WHERE s.language = 'Java'";
    private static final String GET_TOTAL_SALARY_BY_PROJECT_ID = "SELECT SUM(d.salary) FROM developers d " +
            "INNER JOIN developers_projects dp ON dp.dev_id = d.dev_id WHERE dp.project_id = ?";

    private static final String SELECT_ALL = "SELECT dev_id, first_name, last_name, birth_date, gender, " +
            "salary FROM developers";
    private static final String DELETE_BY_ID = "DELETE FROM developers WHERE dev_id=?";


    public  DeveloperRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public DeveloperDao save(DeveloperDao developer) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setDate(3, new Date(developer.getBirthDate().getTime()));
            statement.setString(4, developer.getGender());
            statement.setInt(5, developer.getSalary());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public void update(DeveloperDao developer) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setDate(3, new Date(developer.getBirthDate().getTime()));
            statement.setString(4, developer.getGender());
            statement.setInt(5, developer.getSalary());
            statement.setInt(6, developer.getDevId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't update developer");
        }
    }

    @Override
    public Optional<DeveloperDao> findById(Integer id)  {
        DeveloperDao developerDao = null;
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    developerDao = convert(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return Optional.ofNullable(developerDao);
    }

    public void deleteById(Integer id) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't delete developer");
        }
    }

    @Override
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> developers = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    developers.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get developers");
        }
        return developers;
    }

    public List<DeveloperDao> findAllByProjectId(Integer id) {
        List<DeveloperDao> developers = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_PROJECT_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    convert(resultSet);
                    developers.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return developers;
    }

    public List<DeveloperDao> findAllJavaDevelopers() {
        List<DeveloperDao> developers = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WITH_JAVA_LANGUAGE)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    developers.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return developers;
    }

    public List<DeveloperDao> findAllMiddleDevelopers() {
        List<DeveloperDao> developers = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_WITH_MIDDLE_LEVEL)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    developers.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return developers;
    }

    public Integer getTotalSalaryByProjectId(Integer id) {
        Integer salary = null;
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_TOTAL_SALARY_BY_PROJECT_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    salary = resultSet.getInt("sum");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't get salary");
        }
        return salary;
    }

    private DeveloperDao convert(ResultSet resultSet)  throws SQLException {
        DeveloperDao developerDao = new DeveloperDao();

            developerDao.setDevId(resultSet.getInt("dev_id"));
            developerDao.setFirstName(resultSet.getString("first_name"));
            developerDao.setLastName(resultSet.getString("last_name"));
            developerDao.setGender(resultSet.getString("gender"));
            developerDao.setSalary(resultSet.getInt("salary"));
            developerDao.setBirthDate(resultSet.getDate("birth_date"));

        return developerDao;
    }
}
