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
    private String email;
    private String img;

    public User(String firstName, String lastName, String phoneNumber, String password, String email, String img) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.img = img;
    }

    public User(){}

    public String getToken() {
        return phoneNumber;
    }
}
