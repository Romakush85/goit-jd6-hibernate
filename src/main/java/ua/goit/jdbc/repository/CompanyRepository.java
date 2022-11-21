package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.CompanyDao;
import ua.goit.jdbc.dto.CompanyDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompanyRepository implements Repository<CompanyDao>{
    private final DatabaseManagerConnector connector;

    private final static String INSERT = "INSERT INTO companies ( company_name, country, contact_person, email) " +
            "VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE companies SET  company_name = ?, country = ?, contact_person = ?, " +
            "email = ? WHERE company_id = ?";
    private final static String SELECT_BY_ID = "SELECT company_id, company_name, country, contact_person, email " +
            "FROM companies WHERE company_id = ?";
    private final static String SELECT_ALL = "SELECT company_id, company_name, country, contact_person, email " +
            "FROM companies";
    private final static String DELETE_BY_ID = "DELETE FROM companies WHERE company_id = ?";


    public CompanyRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public CompanyDao save(CompanyDao companyDao) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, companyDao.getName());
            statement.setString(2, companyDao.getCountry());
            statement.setString(3, companyDao.getContactPerson());
            statement.setString(4, companyDao.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyDao;
    }

    @Override
    public void update(CompanyDao companyDao) {
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, companyDao.getName());
            statement.setString(2, companyDao.getCountry());
            statement.setString(3, companyDao.getContactPerson());
            statement.setString(4, companyDao.getEmail());
            statement.setInt(5, companyDao.getCompanyId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CompanyDao> findById(Integer id) {
        CompanyDao companyDao = null;
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    companyDao = convert(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(companyDao);
    }

    @Override
    public List<CompanyDao> findAll() {
        List<CompanyDao> companies = new ArrayList<>();
        try(Connection connection = connector.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    companies.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public void deleteById(Integer id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private CompanyDao convert(ResultSet resultSet) throws SQLException {
        CompanyDao companyDao = new CompanyDao();
        companyDao.setCompanyId(resultSet.getInt("company_id"));
        companyDao.setName(resultSet.getString("company_name"));
        companyDao.setCountry(resultSet.getString("country"));
        companyDao.setContactPerson(resultSet.getString("contact_person"));
        companyDao.setEmail(resultSet.getString("email"));
        return companyDao;
    }
}
