package ua.goit.jdbc.repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.dao.ProjectDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements Repository<ProjectDao>{
    private final HibernateProvider provider;

    public ProjectRepository(HibernateProvider provider) {
        this.provider = provider;
    }


    @Override
    public ProjectDao save(ProjectDao projectDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(projectDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectDao;
    }

    @Override
    public void update(ProjectDao projectDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(projectDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ProjectDao> findById(Integer id)  {
        ProjectDao projectDao = null;
        try (final Session session = provider.openSession()) {
            projectDao = session.get(ProjectDao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(projectDao);
    }

    @Override
    public List<ProjectDao> findAll()  {
        List<ProjectDao> projects = new ArrayList<>();
        try (final Session session = provider.openSession()) {
            return session.createQuery("FROM ProjectDao", ProjectDao.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void deleteById(Integer id) {
        Optional<ProjectDao> optional = findById(id);
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

