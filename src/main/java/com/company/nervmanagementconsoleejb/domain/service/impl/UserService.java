package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.dao.UserServiceDao;
import com.company.nervmanagementconsoleejb.domain.model.Member;
import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService {
    @EJB
    private UserServiceDao userServiceDao;

    public User createUser(String name, String surname, String username, String password,
                           List<Member> defaultMembers) {
        User newUser = User.builder()
                .name(name)
                .surname(surname)
                .username(username)
                .password(password)
                .members(defaultMembers)
                .build();
        return userServiceDao.saveUser(newUser);
    }

    public List<User> getUsersList() {
        return userServiceDao.getUserList();
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        User user = userServiceDao.getUserByUsernameAndPassword(username, password);
        return user;
    }

    public User getUserById(int userId) {
        User user = userServiceDao.getUserById(userId);
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userServiceDao.getUserByUsername(username);
        return user;
    }

    public List<User> getUsersbyNameAndOrSurname(String name, String surname) {
        List<User> listUsers = userServiceDao.getUserList();
        if (name != null) {
            listUsers = listUsers.stream()
                    .filter(user -> user.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }
        if (surname != null) {
            listUsers = listUsers.stream()
                    .filter(user -> user.getSurname().equalsIgnoreCase(surname))
                    .collect(Collectors.toList());
        }
        return listUsers;
    }

    public void updateUser(User u) {
        userServiceDao.updateUser(u);
    }

    public void removeUser(int userId) {
        userServiceDao.removeUser(userId);
    }

}