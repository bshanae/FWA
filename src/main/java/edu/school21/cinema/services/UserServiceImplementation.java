package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserSessionInfo;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.repositories.UserSessionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    @Override
    public User signUp(String firstName, String lastName, String phone, String password, String email, String ip, Long time) {
        User checkUser = userRepository.findUserByPhone(phone);
        if (checkUser != null) {
            // User with that phone already exists
            return null;
        }
        String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
        String img = "default.jpg";
        User user = new User(firstName, lastName, phone, encodedPassword, email, img);
        if (userRepository.createUser(user)) {
            UserSessionInfo userSessionInfo = new UserSessionInfo(user.getId(), ip, time);
            userSessionInfoRepository.create(userSessionInfo);
            return userRepository.findUserByPhone(phone);
        }
        return null;
    }

    public User signIn(String phone, String password, String ip, Long time) {
        User user = userRepository.findUserByPhone(phone);
        if (user != null && (new BCryptPasswordEncoder()).matches(password, user.getPassword())) {
            UserSessionInfo userSessionInfo = new UserSessionInfo(user.getId(), ip, time);
            userSessionInfoRepository.create(userSessionInfo);
            return user;
        }
        return null;
    }

    public User findByToken(String token) {
        //Our TOKEN is the  phone_number
        return userRepository.findUserByToken(token);
    }

    public List<UserSessionInfo> findSessionInfos(int userId) {
        return userSessionInfoRepository.findById(userId);
    }
}