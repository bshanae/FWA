package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserImage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImagesMapper implements RowMapper<UserImage> {
    @Override
    public UserImage mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserImage img = new UserImage();

        img.setId(rs.getInt("id"));
        img.setUserId(rs.getInt("user_id"));
        img.setOriginalName(rs.getString("original_name"));
        img.setName(rs.getString("name"));
        img.setSize(rs.getLong("size"));
        img.setMime(rs.getString("mime"));

        return img;
    }
}
