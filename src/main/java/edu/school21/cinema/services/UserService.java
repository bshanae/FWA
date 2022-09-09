package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public interface UserService {
    User signUp(String firstName, String lastName, String phone, String password, String email, String ip, Long time);

    User signIn(String phone, String password, String ip, Long time);

    User findByToken(String token);
}
