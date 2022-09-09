package edu.school21.cinema.models;

import lombok.Data;

@Data
public class UserSessionInfo {

    private int id;
    private int userId;
    private long time;
    private String ip;
}
