package com.company.nervmanagementconsoleejb.application.web.filter;

import com.company.nervmanagementconsoleejb.application.model.dto.UserDto;
import com.company.nervmanagementconsoleejb.application.utils.Costants;
import com.company.nervmanagementconsoleejb.domain.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/jsp/private/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            HttpServletRequest httpRequest=(HttpServletRequest) request;
            UserDto user=(UserDto) httpRequest.getSession().getAttribute(Costants.KEY_SESSION_USER);

            if(user != null) {
                chain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("/jsp/public/Login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/jsp/public/Login.jsp").forward(request, response);
        }

    }

}