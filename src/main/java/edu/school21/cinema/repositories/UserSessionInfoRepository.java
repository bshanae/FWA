package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSessionInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSessionInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean create(UserSessionInfo session) {
        int result = jdbcTemplate.update(
                "INSERT INTO session_info (user_id, time_millis, ip, date, time) VALUES(?, ?, ?, ?, ?)",
                session.getUserId(),
                session.getTimeMillis(),
                session.getIp(),
                session.getDate(),
                session.getTime());

        return result > 0;
    }

    public List<UserSessionInfo> findById(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM session_info where user_id=?",
                new BeanPropertyRowMapper<>(UserSessionInfo.class),
                userId);
    }

    public void update(UserSessionInfo updatedSession) {
        jdbcTemplate.update(
                "UPDATE session_info SET user_id=?, time=?, ip=? where id=?",
                updatedSession.getUserId(),
                updatedSession.getTime(),
                updatedSession.getIp(),
                updatedSession.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM session_info where id=?", id);
    }
}
