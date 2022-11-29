package ua.goit.jdbc.repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.dao.DeveloperDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperRepository implements Repository<DeveloperDao>{

    private final HibernateProvider provider;
    public DeveloperRepository(HibernateProvider provider) {
        this.provider = provider;
    }


    @Override
    public DeveloperDao save(DeveloperDao developerDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(developerDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return developerDao;
    }

    @Override
    public void update(DeveloperDao developerDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(developerDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<DeveloperDao> findById(Integer id) {
        DeveloperDao developerDao = null;
        try (final Session session = provider.openSession()) {
            developerDao = session.get(DeveloperDao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(developerDao);
    }

    @Override
    public List<DeveloperDao> findAll() {
        List<DeveloperDao> developers = new ArrayList<>();
        try (final Session session = provider.openSession()) {
            return session.createQuery("FROM DeveloperDao", DeveloperDao.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return developers;
    }

    public void deleteById(Integer id) {
        Optional<DeveloperDao> optional = findById(id);
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
