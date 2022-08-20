package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface UserService {
    User signUp(String email, String password);

    User signIn(String email, String password);

    User findByToken(String token);
}
