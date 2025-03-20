package com.company.nervmanagementconsoleejb.application.web.servlet;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.UserDto;
import com.company.nervmanagementconsoleejb.application.utils.Costants;
import com.company.nervmanagementconsoleejb.domain.exception.InvalidCredentialsException;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.RestService;
import com.company.nervmanagementconsoleejb.domain.service.ServletService;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @EJB
    private ServletService servletService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            UserDto userDto = UserDto.builder()
                    .username(req.getParameter(Costants.FORM_LOGIN_USERNAME))
                    .password(req.getParameter(Costants.FORM_LOGIN_PASSWORD))
                    .build();

            User user = mappers.toDomain(userDto);

            user = servletService.login(user);

            userDto = mappers.toDto(user);

            req.getSession().setAttribute(Costants.KEY_SESSION_USER, userDto);
            resp.sendRedirect(req.getContextPath() + "/jsp/private/Home.jsp");
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
            req.setAttribute(Costants.ERROR_LOGIN, e.getMessage());
            req.getRequestDispatcher("/jsp/public/Login.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/public/Error.jsp").forward(req, resp);
        }
    }
}