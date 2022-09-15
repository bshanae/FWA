package edu.school21.cinema.models;

import lombok.Data;

@Data
public class UserImage {

    private int id;
    private int userId;
    private String originalName;
    private String name;
    private Long size;
    private String mime;

    public UserImage(){
    }

    public UserImage(int userId, String originalName, String name, Long size, String mime) {
        this.userId = userId;
        this.originalName = originalName;
        this.name = name;
        this.size = size;
        this.mime = mime;
    }
}
