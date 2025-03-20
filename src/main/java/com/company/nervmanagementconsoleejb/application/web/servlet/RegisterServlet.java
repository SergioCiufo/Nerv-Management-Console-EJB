package com.company.nervmanagementconsoleejb.application.web.servlet;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.UserDto;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.ServletService;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @EJB
    private ServletService servletService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserDto userDto = UserDto.builder()
                    .name(req.getParameter("registerName"))
                    .surname(req.getParameter("registerSurname"))
                    .username(req.getParameter("registerUsername"))
                    .password(req.getParameter("registerPassword"))
                    .build();

            User user = mappers.toDomain(userDto);

            servletService.register(user);
            req.getRequestDispatcher("/jsp/public/Login.jsp").forward(req, resp);
        } catch (Exception e) { // si potrebbe gestire l'eccezione "username già esistente blabla"
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/public/Error.jsp").forward(req, resp);
        }
    }
}
