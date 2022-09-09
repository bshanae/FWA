package edu.school21.cinema.servlets;

import edu.school21.cinema.SessionKey;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSessionInfo;
import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends CinemaServlet {
    @Autowired
    @Qualifier("jspProfile")
    private String jspProfile;

    @Autowired
    private UserServiceImplementation userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = (String) request.getSession().getAttribute(SessionKey.TOKEN);
        System.out.println("TOKEN " + token);
        User user = userService.findByToken(token);
        List<UserSessionInfo> userSessionInfos = userService.findSessionInfos(user.getId());

        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("phone", user.getPhoneNumber());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("img", user.getImg());
        request.setAttribute("sessionInfo", userSessionInfos.toString());
        request.getRequestDispatcher(jspProfile).forward(request, response);

    }
}
