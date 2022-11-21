package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.DeveloperDao;
import ua.goit.jdbc.dao.ProjectDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements Repository<ProjectDao>{
    private final DatabaseManagerConnector connector;

    private final static String FIND_ALL = "SELECT project_id, project_name, customer_id, cost FROM projects;";
    private final static String INSERT = "INSERT INTO projects (project_name, customer_id, cost) " +
            "VALUES (?, ?, ?)";
    private final static String FIND_BY_ID = "SELECT project_id, project_name, customer_id, cost FROM projects " +
            "WHERE project_id = ?";
    private final static String UPDATE_BY_ID = "UPDATE projects SET project_name = ?, customer_id = ?, cost = ? " +
            "WHERE project_id = ?";
    private final static String DELETE_BY_ID = "DELETE FROM projects WHERE project_id = ?";

    public ProjectRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public ProjectDao save(ProjectDao project) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, project.getName());
            statement.setInt(2, project.getCustomerId());
            statement.setInt(3, project.getCost());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void update(ProjectDao project) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID)) {
            statement.setString(1, project.getName());
            statement.setInt(2, project.getCustomerId());
            statement.setInt(3, project.getCost());
            statement.setInt(4, project.getProjectId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't update project");
        }
    }

    @Override
    public Optional<ProjectDao> findById(Integer id)  {
        ProjectDao projectDao = null;
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    projectDao = convert(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't find project");
        }
        return Optional.ofNullable(projectDao);
    }

    @Override
    public List<ProjectDao> findAll() {
        List<ProjectDao> projects = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    projects.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
        return projects;
    }

    public void deleteById(Integer id) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't delete project");
        }
    }

    private ProjectDao convert(ResultSet resultSet)  throws SQLException {
        ProjectDao projectDao = new ProjectDao();

        projectDao.setProjectId(resultSet.getInt("project_id"));
        projectDao.setName(resultSet.getString("project_name"));
        projectDao.setCustomerId(resultSet.getInt("customer_id"));
        projectDao.setCost(resultSet.getInt("cost"));

        return projectDao;
    }
}

