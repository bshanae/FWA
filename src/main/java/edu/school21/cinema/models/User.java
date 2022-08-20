package edu.school21.cinema.models;

import lombok.Getter;

@Getter
public class User {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String password;

    public User(String firstName, String lastName, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getToken() {
        return phoneNumber;
    }
}
