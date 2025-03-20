package com.company.nervmanagementconsoleejb.domain.dao;

import com.company.nervmanagementconsoleejb.domain.model.User;
import javax.ejb.Local;

import java.util.List;

@Local
public interface UserServiceDao {
    public void create(User user);
    public User saveUser(User user);
    public List<User> getUserList();
    public User getUserByUsernameAndPassword(String username, String password);
    public User getUserById(int userId);
    public User getUserByUsername(String username);
    public void updateUser(User user);
    public void removeUser(int userId);
}
