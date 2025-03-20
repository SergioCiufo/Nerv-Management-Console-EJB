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

@WebServlet("/mission")
public class MissionServlet extends HttpServlet {

    private final Mappers mappers = org.mapstruct.factory.Mappers.getMapper(Mappers.class);

    @EJB
    private ServletService servletService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
//            User user=(User)req.getSession().getAttribute(Costants.KEY_SESSION_USER);
//            String idMissionString = req.getParameter("missionId");
//
//            String [] selectedIdMembers = req.getParameterValues("memberSelect");
//            if(selectedIdMembers != null) {
//                ArrayList<String> selectedIdMembersListString = new ArrayList<>(Arrays.asList(selectedIdMembers));
//                user=missionSendOrCompleteService.sendMembersMission(user, idMissionString, selectedIdMembersListString);
//            }
//            req.getSession().setAttribute(Costants.KEY_SESSION_USER, user);
//            resp.sendRedirect(req.getContextPath() + "/jsp/private/Home.jsp");


//            FirstStepMissionRequestApp firstStepMissionRequestApp = FirstStepMissionRequestApp.builder()
////                    .user((UserDto) req.getSession().getAttribute(Costants.KEY_SESSION_USER))
////                    .missionId(req.getParameter("missionId"))
////                    .selectedIdMembers(req.getParameterValues("memberSelect"))
////                    .build();

//            FirstStepMissionRequest firstStepMissionRequest = servletMappers.convertToDomain(firstStepMissionRequestApp);
//
//
//            if (firstStepMissionRequest.getSelectedIdMembers() != null) {
//                FirstStepMissionResponse firstStepMissionResponse = servletService.mission(firstStepMissionRequest);
//                userDto = servletMappers.convertFromDomain(firstStepMissionResponse);
//            }

            UserDto userDto = (UserDto) req.getSession().getAttribute(Costants.KEY_SESSION_USER);
            String idMissionString = req.getParameter("missionId");
            String selectedIdMembers = req.getParameter("memberSelect");

            User user = mappers.toDomain(userDto);
            user = servletService.mission(user, idMissionString, selectedIdMembers);

            userDto = mappers.toDto(user);

            req.getSession().setAttribute(Costants.KEY_SESSION_USER, userDto);
            resp.sendRedirect(req.getContextPath() + "/jsp/private/Home.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/jsp/public/Error.jsp").forward(req, resp);
        }
    }
}