package ua.goit.jdbc.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T save(T entity);
    void update(T entity);
    Optional<T> findById(Integer id) throws SQLException;
    List<T> findAll() throws SQLException;

}
