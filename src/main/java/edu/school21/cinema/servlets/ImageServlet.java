package edu.school21.cinema.servlets;


import edu.school21.cinema.SessionKey;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.UserServiceImplementation;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@WebServlet("/images/*")
@MultipartConfig
public class ImageServlet extends CinemaServlet {

    @Autowired
    UserServiceImplementation userService;

    @Autowired
    @Qualifier("profileImage")
    private String profileImage;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute(SessionKey.TOKEN);
        User user = userService.findByToken(token);
        String filename = request.getRequestURI().substring(request.getRequestURI().indexOf("/", 1) + 1);
        String filename1 = filename.substring(filename.indexOf("/", 1) + 1);
        String file = userService.getUserImage(filename1, user.getId());

        response.setContentType("text/html");
        request.setAttribute("image", file);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(profileImage);
        requestDispatcher.forward(request, response);
    }


}

