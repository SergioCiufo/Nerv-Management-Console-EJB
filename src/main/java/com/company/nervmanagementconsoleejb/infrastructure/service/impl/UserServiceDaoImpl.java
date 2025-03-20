package com.company.nervmanagementconsoleejb.infrastructure.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.UserServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.infrastructure.atomicDao.UserDaoAtomic;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
//da controllare l'injezione
@Stateless
public class UserServiceDaoImpl implements UserServiceDao {

    @PersistenceContext(unitName = "NervManagementConsole")
    private EntityManager entityManager;

    @EJB
    private UserDaoAtomic userDaoAtomic;

    @Override
    public User saveUser(User user) {
        userDaoAtomic.create(user);
        user.setIdUser(userDaoAtomic.getUserByUsername(user.getUsername()).getIdUser());
        return user;
    }

    @Override
    public void create(User user) {
        userDaoAtomic.create(user);
    }

    @Override
    public List<User> getUserList() {
        return userDaoAtomic.retrieve();
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userDaoAtomic.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserById(int userId) {
        return userDaoAtomic.getUserById(userId);

    }

    @Override
    public User getUserByUsername(String username) {
        return userDaoAtomic.getUserByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userDaoAtomic.updateUser(user);
    }

    @Override
    public void removeUser(int userId) {
        userDaoAtomic.removeUser(userId);
    }
}
