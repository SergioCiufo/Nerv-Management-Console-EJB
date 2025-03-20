package com.company.nervmanagementconsoleejb.domain.service.impl;

import com.company.nervmanagementconsoleejb.domain.exception.InvalidCredentialsException;
import com.company.nervmanagementconsoleejb.domain.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class LoginService {
    @EJB
    private UserService userService;
    @EJB
    private RetriveInformationService ris;

    public User loginCheck(String username, String password) throws Exception {
        User user = null;
        user = userService.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw new InvalidCredentialsException("Invalid Credentials", null);
        }
        user=ris.retriveUserInformation(user);
        return user;
    }

}