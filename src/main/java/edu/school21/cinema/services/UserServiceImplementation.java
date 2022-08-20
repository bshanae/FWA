package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

public class UserServiceImplementation implements UserService {
    public User signUp(String firstName, String lastName, String phone, String password) {
        return new User(firstName, lastName, phone, password);
    }

    public User signIn(String phone, String password) {
        if (password.equals("0"))
            return new User("Vladimir", "Lenin", phone, password);

        return null;
    }

    public User findByToken(String token) {
        return new User("Vladimir", "Lenin", token, "8(880)555-35-35");
    }
}