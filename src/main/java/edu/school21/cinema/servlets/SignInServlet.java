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

@WebServlet("/sign-in")
public class SignInServlet extends CinemaServlet {
    @Autowired
    @Qualifier("jspSignIn")
    private String jspSignIn;

    @Autowired
    @Qualifier("uriProfile")
    private String uriProfile;

    @Autowired
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(jspSignIn).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        if (phone == null || password == null) {
            response.getWriter().write("Invalid request!");
            return;
        }

        User user = userService.signIn(phone, password);
        if (user == null) {
            response.getWriter().write("User not found!");
            return;
        }

        request.getSession().setAttribute(SessionKey.TOKEN, user.getToken());
        response.sendRedirect(uriProfile);
    }
}
