package edu.school21.cinema.services;

public class UserServiceImplementation implements UserService {
    public boolean signUp(String email, String password) {
        return true;
    }

    public boolean signIn(String email, String password) {
        return password.equals("0");
    }
}