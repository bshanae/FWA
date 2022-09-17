package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserImage;
import edu.school21.cinema.models.UserSessionInfo;
import edu.school21.cinema.repositories.UserImagesRepository;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;
    @Autowired
    private UserImagesRepository userImagesRepository;
    @Autowired
    @Qualifier("imgSaveUrl")
    private String imgSaveUrl;

    @Override
    public User signUp(String firstName, String lastName, String phone, String password, String email, String ip, Long timeMillis) {
        User checkUser = userRepository.findUserByPhone(phone);
        if (checkUser != null) {
            return null;
        }

        String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
        String img = "default.jpg";
        User user = new User(firstName, lastName, phone, encodedPassword, email, img);
        if (userRepository.createUser(user)) {
            String[] dateInfo = (new Date(timeMillis)).toString().split(" ");
            String date = dateInfo[0] + " " + dateInfo[1] + " " + dateInfo[2] + " " + dateInfo[5];
            String time = dateInfo[3];
            UserSessionInfo userSessionInfo = new UserSessionInfo(user.getId(), timeMillis, ip, date, time);
            userSessionInfoRepository.create(userSessionInfo);
            return userRepository.findUserByPhone(phone);
        }
        return null;
    }

    public User signIn(String phone, String password, String ip, Long timeMillis) {
        User user = userRepository.findUserByPhone(phone);
        if (user != null && (new BCryptPasswordEncoder()).matches(password, user.getPassword())) {
            String[] dateInfo = (new Date(timeMillis)).toString().split(" ");
            String date = dateInfo[0] + " " + dateInfo[1] + " " + dateInfo[2] + " " + dateInfo[5];
            String time = dateInfo[3];
            UserSessionInfo userSessionInfo = new UserSessionInfo(user.getId(), timeMillis, ip, date, time);
            userSessionInfoRepository.create(userSessionInfo);
            return user;
        }
        return null;
    }

    public User findByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    public void saveUserAvatar(UserImage img, int userId) {
        userImagesRepository.create(img);
        User user = userRepository.findUserById(userId);
        user.setImg(img.getOriginalName());
        userRepository.update(user);
    }

    public List<UserImage> getUserAvatars(int userId) {
        List<UserImage> list = userImagesRepository.findById(userId);
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public String getUserImage(String filename, int userId) {
        String img = "";
        try {
            List<UserImage> list = userImagesRepository.findById(userId);

            UserImage imgData = list.stream()
                                    .filter(element -> element.getOriginalName().equals(filename))
                                    .findAny()
                                    .orElse(null);
            if (imgData == null)
                imgData = new UserImage(0, filename, filename, 0L, "image/jpg");

            String extension = imgData.getMime().substring(imgData.getMime().indexOf("/", 1) + 1);

            byte[] bytes;
            int read;
            try (FileInputStream fileInputStream = new FileInputStream(imgSaveUrl + imgData.getName())) {
                bytes = new byte[fileInputStream.available()];
                read = fileInputStream.read(bytes);
            }

            if (read == 0) {
                return "";
            }

            img = "data:image/" + extension + ";base64," + Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public List<UserSessionInfo> findSessionInfos(int userId) {
        return userSessionInfoRepository.findById(userId);
    }
}