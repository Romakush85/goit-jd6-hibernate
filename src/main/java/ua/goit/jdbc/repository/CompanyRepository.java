package ua.goit.jdbc.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.dao.CompanyDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CompanyRepository implements Repository<CompanyDao>{

    private final HibernateProvider provider;
    public CompanyRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public CompanyDao save(CompanyDao companyDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(companyDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyDao;
    }

    @Override
    public void update(CompanyDao companyDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(companyDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<CompanyDao> findById(Integer id) {
        CompanyDao companyDao = null;
        try (final Session session = provider.openSession()) {
            companyDao = session.get(CompanyDao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(companyDao);
    }


    @Override
    public List<CompanyDao> findAll() {
        List<CompanyDao> companies = new ArrayList<>();
        try (final Session session = provider.openSession()) {
            return session.createQuery("FROM CompanyDao", CompanyDao.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }

    public void deleteById(Integer id) {
        Optional<CompanyDao> optional = findById(id);
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
