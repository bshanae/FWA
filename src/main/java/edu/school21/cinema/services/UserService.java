package edu.school21.cinema.services;

public interface UserService {
    boolean signUp(String email, String password);

    boolean signIn(String email, String password);
}
