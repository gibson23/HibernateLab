package ua.hypson.hibernatelab.service;

import ua.hypson.hibernatelab.dao.impl.HibernateUserDaoImpl;
import ua.hypson.hibernatelab.dao.interfaces.UserDao;
import ua.hypson.hibernatelab.entity.User;
import ua.hypson.hibernatelab.exception.InvalidUserInputException;

import java.util.List;

public class LoginService {

    private final UserDao userDao;

    public LoginService() {
        userDao = new HibernateUserDaoImpl();
    }

    public User login(String login, String password) {
        User fetched = userDao.findByLogin(login);
        if (null == fetched) {
            throw new InvalidUserInputException("Bad credentials");
        } else if (!password.equals(fetched.getPassword())) {
            throw new InvalidUserInputException("Bad credentials");
        }

        return fetched;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
