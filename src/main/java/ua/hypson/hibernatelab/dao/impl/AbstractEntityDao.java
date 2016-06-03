package ua.hypson.hibernatelab.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.hypson.hibernatelab.dao.HibernateUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 02.06.2016.
 */
public abstract class AbstractEntityDao<T> {

    private final SessionFactory sessionFactory = new HibernateUtils().getFactory();

    protected abstract Class<T> getEntityClass();

    protected interface Callback {
        Object invoke(Session session);
    }

    protected final Object invokeTransactional(Callback callback) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object result = callback.invoke(session);
            transaction.commit();
            return result;
        } catch (RuntimeException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception e1) {
                    throw new RuntimeException("Rollback failed", e1);
                }
            }
        } finally {
            closeSession(session);
        }
        return null;
    }

    protected final Object executeSelect(Callback callback) {
        Session session = sessionFactory.openSession();
        try {
            return callback.invoke(session);
        } finally {
            closeSession(session);
        }
    }

    private void closeSession(Session session) {
        if (session != null) {
            try {
//                session.flush();
                session.close();
            } catch (HibernateException e) {
                throw new RuntimeException("failed close session", e);
            }
        }
    }

    public void save(T t) {
        invokeTransactional((Session session) -> {
            session.save(t);
            return null;
        });
    }

    public void create(T t) {
        save(t);
    }

    public void update(T t) {
        invokeTransactional((Session session) -> {
            session.update(t);
            return null;
        });
    }

    public void delete(T t) {
        invokeTransactional((Session session) -> {
            session.delete(t);
            return null;
        });
    }

    public void remove(T t) {
        delete(t);
    }

    public T findById(Serializable id) {

        //noinspection unchecked
        return (T) executeSelect((Session session) -> session.get(getEntityClass(), id));
    }

    public List<T> findAll() {
        //noinspection unchecked
        return (List<T>) executeSelect(session -> session.createQuery("from " + getEntityClass().getName()).list());
    }
}
