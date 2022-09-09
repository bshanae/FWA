package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
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
        return jdbcTemplate.update("INSERT INTO session_info (user_id, time, ip_address)" +
                " VALUES(?, ?, ?)", session.getUserId(), session.getTime(), session.getIp()) > 0;
    }

    public List<UserSessionInfo> findById(int userId) {
        return jdbcTemplate.query("SELECT * FROM session_info where user_id=?", new BeanPropertyRowMapper<>(UserSessionInfo.class), userId);
    }

    //not necesary
    public void update(UserSessionInfo updatedSession) {
        jdbcTemplate.update("UPDATE session_info SET user_id=?, time=?, ip_address=?", updatedSession.getUserId(),
                updatedSession.getTime(), updatedSession.getIp());
    }

    //Use in case if user delete his profile?
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM session_info where id=?", id);
    }
}
