package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserImage;
import edu.school21.cinema.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServlet;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
/* The Java file upload Servlet example */

@WebServlet("/upload-file" )
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UploadFileServlet extends CinemaServlet {

    @Autowired
    @Qualifier("imgSaveUrl")
    private String imgSaveUrl;

    @Autowired
    @Qualifier("uriProfile")
    private String uriProfile;

    @Autowired
    private UserServiceImplementation userService;


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phone = request.getParameter("phone");
        Part filePart = request.getPart("file");
        String contentType = filePart.getContentType();
        Long fileSize = filePart.getSize();
        String originalFileName = filePart.getSubmittedFileName();
        User user = userService.findByToken(phone);
        List<UserImage> images = userService.getUserAvatars(user.getId());
        Integer idNumber = images.stream().mapToInt(element -> element.getId()).max().orElse(0);
        if (idNumber == null) {
            idNumber = 0;
        }
        String fileName = "user_" + user.getId() + "_img_" + idNumber +"_" + originalFileName;
        UserImage img = new UserImage(user.getId(), originalFileName, fileName, fileSize, contentType);
        for (Part part : request.getParts()) {
            String fileUrl =  imgSaveUrl + fileName;
            part.write(fileUrl);
        }
        userService.saveUserAvatar(img, user.getId());
        response.sendRedirect(uriProfile);
        // response.getWriter().print("The file was uploaded sucessfully.");
    }

}
