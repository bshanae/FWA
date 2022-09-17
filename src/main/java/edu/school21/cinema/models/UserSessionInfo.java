package edu.school21.cinema.models;

import lombok.Data;

@Data
public class UserSessionInfo {

    private int id;
    private int userId;
    private long timeMillis;
    private String ip;
    private String date;
    private String time;

    public UserSessionInfo(int userId, Long timeMillis, String ip, String date, String time) {
        this.userId = userId;
        this.timeMillis = timeMillis;
        this.ip = ip;
        this.date = date;
        this.time = time;
    }

    public UserSessionInfo() {
    }
}
