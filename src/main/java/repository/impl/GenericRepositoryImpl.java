package repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.GenericEntity;
import repository.GenericRepository;

import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepositoryImpl<T extends GenericEntity> implements GenericRepository<T> {

    public static SessionFactory getFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    private String tableName;
    private Class<T> clazz;

    private void getTableName() {
        Class<T> clazz = ((Class)((ParameterizedType)this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]);
        this.clazz = clazz;

        Table tableName = clazz.getAnnotation(Table.class);
        this.tableName = tableName.name().replaceAll("`", "");
    }

    private SessionFactory sessionFactory;

    protected GenericRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        getTableName();
    }

    public void insert(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(entity);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOrReplace(T toReplace) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            T entity = session.get(clazz, toReplace.getEntityId());

            if (entity != null) {
                session.delete(entity);
            }
            session.save(toReplace);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(T toReplace, String... properties) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            T entity = session.get(clazz, toReplace.getEntityId());

            if (entity == null) {
                return;
            }

            for (String property : properties) {
                try {
                    Field field = clazz.getDeclaredField(property);
                    field.setAccessible(true);
                    field.set(entity, field.get(toReplace));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                }
            }

            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> searchAll() {
        List<T> list = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            list = session.createQuery("from " + tableName, clazz).list();

            return list;
        } catch (Exception ignored) {
            return list;
        }
    }

    public T findById(T entity) {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            return session.get(clazz, entity.getEntityId());
        } catch (Exception ignored) {
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public T delete(T toDelete) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            T entity = session.load(clazz, toDelete.getEntityId());
            if (entity != null) {
                session.delete(entity);
                session.getTransaction().commit();
            }
            return entity;
        } catch (Exception ignored) {
            return null;
        }
    }

}
