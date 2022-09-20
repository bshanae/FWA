package edu.school21.cinema.servlets;

import edu.school21.cinema.SessionKey;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserImage;
import edu.school21.cinema.models.UserSessionInfo;
import edu.school21.cinema.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        User user = userService.findByToken(token);

        List<UserSessionInfo> userSessionInfos = userService.findSessionInfos(user.getId());
        List<UserImage> userImages = userService.getUserAvatars(user.getId());
        String img = userService.getUserImage(user.getImg(), user.getId());

        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("phone", user.getPhoneNumber());
        request.setAttribute("email", user.getEmail());
        request.setAttribute("img", user.getImg());
        request.setAttribute("sessionInfo", userSessionInfos);
        request.setAttribute("images", userImages);
        request.setAttribute("profileImage", img);

        request.getRequestDispatcher(jspProfile).forward(request, response);
    }
}
