package ua.hypson.hibernatelab.dao.impl;

import org.hibernate.Session;
import ua.hypson.hibernatelab.dao.interfaces.UserDao;
import ua.hypson.hibernatelab.entity.User;

/**
 * Created by admin on 02.06.2016.
 */
public class HibernateUserDaoImpl extends AbstractEntityDao<User> implements UserDao {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    public User findByLogin(String login) {
        return (User) executeSelect((Session session) -> session.createQuery("from User u where u.login = :login")
                .setString("login", login).uniqueResult());
    }

    public User findByEmail(String email) {
        return (User) executeSelect((Session session) -> session.createQuery("from User u where u.email = :email")
                .setString("email", email).uniqueResult());
    }
}
