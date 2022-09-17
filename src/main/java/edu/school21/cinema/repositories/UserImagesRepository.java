package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserImage;
import edu.school21.cinema.models.UserSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserImagesRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserImagesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean create(UserImage img) {
        int result = jdbcTemplate.update(
                "INSERT INTO user_images (user_id, original_name, name, size, mime) VALUES(?, ?, ?, ? , ?)",
                img.getUserId(),
                img.getOriginalName(),
                img.getName(),
                img.getSize(),
                img.getMime());

        return result > 0;
    }

    public List<UserImage> findById(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM user_images where user_id=?",
                new BeanPropertyRowMapper<>(UserImage.class),
                userId);
    }

}
