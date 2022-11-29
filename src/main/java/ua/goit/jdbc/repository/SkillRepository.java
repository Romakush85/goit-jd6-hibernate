package ua.goit.jdbc.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.jdbc.config.HibernateProvider;
import ua.goit.jdbc.dao.SkillDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepository implements Repository<SkillDao>{
    private final HibernateProvider provider;
    public SkillRepository(HibernateProvider provider) {
        this.provider = provider;
    }

    @Override
    public SkillDao save(SkillDao skillDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.persist(skillDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skillDao;
    }

    @Override
    public void update(SkillDao skillDao) {
        try (final Session session = provider.openSession()) {
            final Transaction transaction = session.beginTransaction();
            session.merge(skillDao);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<SkillDao> findById(Integer id)  {
        SkillDao skillDao = null;
        try (final Session session = provider.openSession()) {
            skillDao = session.get(SkillDao.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(skillDao);
    }

    @Override
    public List<SkillDao> findAll()  {
        List<SkillDao> skills = new ArrayList<>();
        try (final Session session = provider.openSession()) {
            return session.createQuery("FROM SkillDao", SkillDao.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return skills;
    }

    public void deleteById(Integer id) {
        Optional<SkillDao> optional = findById(id);
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
