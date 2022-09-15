package edu.school21.cinema.models;

import lombok.Data;

import java.util.Date;

@Data
public class UserSessionInfo {

    private int id;
    private int userId;
    private long timeMilisec;
    private String ip;
    private String date;
    private String time;

    public UserSessionInfo(int userId, Long timeMilisec,String ip, String date, String time){
        this.userId = userId;
        this.timeMilisec = timeMilisec;
        this.ip = ip;
        this.date = date;
        this.time = time;
    }

    public UserSessionInfo() {

    }
}
