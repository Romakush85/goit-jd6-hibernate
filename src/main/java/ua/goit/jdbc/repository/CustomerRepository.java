package ua.goit.jdbc.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ua.goit.jdbc.config.HibernateProvider;

import ua.goit.jdbc.dao.CustomerDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepository implements Repository<CustomerDao> {
    private final HibernateProvider provider;

    public CustomerRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public CustomerDao save(CustomerDao customerDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(customerDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerDao;
    }

    @Override
    public void update(CustomerDao customerDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(customerDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CustomerDao> findById(Integer id){
        CustomerDao customerDao = null;
        try (final Session session = provider.openSession()) {
            customerDao = session.get(CustomerDao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(customerDao);
    }

    @Override
    public List<CustomerDao> findAll()  {
        List<CustomerDao> customers = new ArrayList<>();
        try (final Session session = provider.openSession()) {
            return session.createQuery("FROM CustomerDao", CustomerDao.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void deleteById(Integer id){
        Optional<CustomerDao> optional = findById(id);
        if(optional.isPresent()) {
            try (final Session session = provider.openSession()) {
                final Transaction transaction = session.beginTransaction();
                session.remove(optional.get());
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
