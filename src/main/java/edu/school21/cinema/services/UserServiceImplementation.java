package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public class UserServiceImplementation implements UserService {
    public User signUp(String email, String password) {
        return new User(email, password);
    }

    public User signIn(String email, String password) {
        if (password.equals("0"))
            return new User(email, password);

        return null;
    }

    public User findByToken(String token) {
        return new User(token, "_");
    }
}