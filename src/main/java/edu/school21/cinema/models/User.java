package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String email;
    private String password;

    public User(String email, String password) {
       this.email = email;
       this.password = password;
    }

    public String getToken() {
        return email;
    }
}
