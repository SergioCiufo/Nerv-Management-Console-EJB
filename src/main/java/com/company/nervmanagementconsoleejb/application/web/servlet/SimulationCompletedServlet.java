package com.company.nervmanagementconsoleejb.application.web.servlet;

import com.company.nervmanagementconsoleejb.application.Mappers;
import com.company.nervmanagementconsoleejb.application.model.dto.UserDto;
import com.company.nervmanagementconsoleejb.application.utils.Costants;
import com.company.nervmanagementconsoleejb.domain.model.User;
import com.company.nervmanagementconsoleejb.domain.service.ServletService;
import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/simulationCompleted")
public class SimulationCompletedServlet extends HttpServlet {

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @EJB
    private ServletService servletService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            User user=(User)req.getSession().getAttribute(Costants.KEY_SESSION_USER);
//            String idMember= req.getParameter("memberSelect");
//            String idSimulation = req.getParameter("simulationId");
//
//            user=simulationSendOrCompleteService.completeSimulation(user, idMember, idSimulation);
//
//            req.getSession().setAttribute(Costants.KEY_SESSION_USER, user);
//            resp.sendRedirect(req.getContextPath() + "/jsp/private/Home.jsp");
//            SecondStepSimulationCompletedRequestApp secondStepSimulationCompletedRequestApp = SecondStepSimulationCompletedRequestApp.builder()
//                    .user((UserDto) req.getSession().getAttribute(Costants.KEY_SESSION_USER))
//                    .idMember(req.getParameter("memberSelect"))
//                    .idSimulation(req.getParameter("simulationId"))
//                    .build();
//
//            SecondStepSimulationCompletedRequest secondStepSimulationCompletedRequest = servletMappers.convertToDomain(secondStepSimulationCompletedRequestApp);
//
//            SecondStepSimulationCompletedResponse secondStepSimulationCompletedResponse = servletService.completeSimulation(secondStepSimulationCompletedRequest);
//
//            UserDto userDto = servletMappers.convertFromDomain(secondStepSimulationCompletedResponse);
            UserDto userDto=(UserDto) req.getSession().getAttribute(Costants.KEY_SESSION_USER);
            String idMember= req.getParameter("memberSelect");
            String idSimulation = req.getParameter("simulationId");

            User user = mappers.toDomain(userDto);

            user=servletService.completeSimulation(user, idMember, idSimulation);

            userDto = mappers.toDto(user);
            req.getSession().setAttribute(Costants.KEY_SESSION_USER, userDto);
            resp.sendRedirect(req.getContextPath() + "/jsp/private/Home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/public/Error.jsp").forward(req, resp);
        }
    }
}
