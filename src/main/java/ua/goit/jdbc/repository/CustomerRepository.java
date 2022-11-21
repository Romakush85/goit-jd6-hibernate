package ua.goit.jdbc.repository;

import ua.goit.jdbc.config.DatabaseManagerConnector;
import ua.goit.jdbc.dao.CompanyDao;
import ua.goit.jdbc.dao.CustomerDao;
import ua.goit.jdbc.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<CustomerDao> {
    private final DatabaseManagerConnector connector;

    private final static String INSERT = "INSERT INTO customers ( customer_name, country, contact_person, email) " +
            "VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE customers SET  customer_name = ?, country = ?, contact_person = ?, " +
            "email = ? WHERE customer_id = ?";
    private final static String SELECT_BY_ID = "SELECT customer_id, customer_name, country, contact_person, email " +
            "FROM customers WHERE customer_id = ?";
    private final static String SELECT_ALL = "SELECT customer_id, customer_name, country, contact_person, email " +
            "FROM customers";
    private final static String DELETE_BY_ID = "DELETE FROM customers WHERE customer_id = ?";


    public CustomerRepository(DatabaseManagerConnector connector) {
        this.connector = connector;
    }

    @Override
    public CustomerDao save(CustomerDao customerDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, customerDao.getName());
            statement.setString(2, customerDao.getCountry());
            statement.setString(3, customerDao.getContactPerson());
            statement.setString(4, customerDao.getEmail());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDao;
    }

    @Override
    public void update(CustomerDao customerDao) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, customerDao.getName());
            statement.setString(2, customerDao.getCountry());
            statement.setString(3, customerDao.getContactPerson());
            statement.setString(4, customerDao.getEmail());
            statement.setInt(5, customerDao.getCustomerId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomerDao> findById(Integer id) {
        CustomerDao customerDao = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customerDao = convert(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(customerDao);
    }

    @Override
    public List<CustomerDao> findAll() {
        List<CustomerDao> customers = new ArrayList<>();
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(convert(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
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

    private CustomerDao convert(ResultSet resultSet) throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setCustomerId(resultSet.getInt("customer_id"));
        customerDao.setName(resultSet.getString("customer_name"));
        customerDao.setCountry(resultSet.getString("country"));
        customerDao.setContactPerson(resultSet.getString("contact_person"));
        customerDao.setEmail(resultSet.getString("email"));
        return customerDao;
    }
}
