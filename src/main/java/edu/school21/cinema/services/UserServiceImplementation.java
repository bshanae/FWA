package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(String firstName, String lastName, String phone, String password) {
        if (userRepository.createUser(firstName, lastName, phone, password))
            return userRepository.findUserByPhone(phone);
        return null;
    }

    public User signIn(String phone, String password) {
        User user = userRepository.findUserByPhone(phone);
        //TODO check encrypted password
        if (user != null) {
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