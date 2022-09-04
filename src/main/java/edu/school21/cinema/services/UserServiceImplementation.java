package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(String firstName, String lastName, String phone, String password) {
        User checkUser = userRepository.findUserByPhone(phone);
        if (checkUser != null) {
            // User with that phone already exists
            return null;
        }
        String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
        User user = new User(firstName, lastName, phone, encodedPassword);
        if (userRepository.createUser(user))
            return userRepository.findUserByPhone(phone);
        return null;
    }

    public User signIn(String phone, String password) {
        User user = userRepository.findUserByPhone(phone);
        String encodedPassword = (new BCryptPasswordEncoder()).encode(password);
        if (user != null && user.getPassword().equals(encodedPassword)) {
            return user;
        }
        return null;
    }

    public User findByToken(String token) {
        //Check token
        //TODO check token in current session
        return userRepository.findUserByToken(token);
    }
}