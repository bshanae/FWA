package edu.school21.cinema.servlets;

import edu.school21.cinema.SessionKey;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends CinemaServlet {
    @Autowired
    @Qualifier("jspSignUp")
    private String jspSignUp;

    @Autowired
    @Qualifier("uriProfile")
    private String uriProfile;

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(jspSignUp).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String ipAddress =  request.getRemoteAddr();
        Long time = request.getSession().getLastAccessedTime();
        if (firstName == null || lastName == null || phone == null || password == null) {
            response.getWriter().write("Invalid request!");
            return;
        }

        User user = userService.signUp(firstName, lastName, phone, password, email, ipAddress, time);
        if (user == null) {
            response.getWriter().write("Can't create user!");
            return;
        }

        request.getSession().setAttribute(SessionKey.TOKEN, user.getToken());
        response.sendRedirect(uriProfile);
    }
}
