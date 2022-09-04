package edu.school21.cinema.models;

import lombok.Data;
import lombok.Getter;


@Data
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    public User(int id, String firstName, String lastName, String phoneNumber, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User(){}

    public String getToken() {
        return phoneNumber;
    }
}
