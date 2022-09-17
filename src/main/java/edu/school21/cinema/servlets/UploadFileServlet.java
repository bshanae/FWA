package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserImage;
import edu.school21.cinema.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/upload-file" )
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
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

        int idNumber = images.stream().mapToInt(UserImage::getId).max().orElse(0);
        String fileName = "user_" + user.getId() + "_img_" + idNumber +"_" + originalFileName;
        UserImage img = new UserImage(user.getId(), originalFileName, fileName, fileSize, contentType);

        for (Part part : request.getParts()) {
            String fileUrl =  imgSaveUrl + fileName;
            part.write(fileUrl);
        }

        userService.saveUserAvatar(img, user.getId());
        response.sendRedirect(uriProfile);
    }
}
