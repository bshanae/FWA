package edu.school21.cinema.servlets;

import edu.school21.cinema.SessionKey;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/profile")
public class ProfileServlet extends CinemaServlet {
    @Autowired
    @Qualifier("jspProfile")
    private String jspProfile;

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String token = (String) request.getSession().getAttribute(SessionKey.TOKEN);
        User user = userService.findByToken(token);

        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("phone", user.getPhoneNumber());
        request.getRequestDispatcher(jspProfile).forward(request, response);

        ServletOutputStream out = response.getOutputStream();
        out.write("Getting user data".getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();

    }
}
