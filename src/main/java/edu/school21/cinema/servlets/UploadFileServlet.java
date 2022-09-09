package edu.school21.cinema.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServlet;

import java.io.*;
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        for (Part part : request.getParts()) {
            String fileUrl =  imgSaveUrl + fileName;
                    part.write(fileUrl);
        }
        response.getWriter().print("The file was uploaded sucessfully.");
    }

}
