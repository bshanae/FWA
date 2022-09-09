package edu.school21.cinema.repositories;

import edu.school21.cinema.models.UserSessionInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSessionInfoMapper implements RowMapper<UserSessionInfo> {

    @Override
    public UserSessionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserSessionInfo userSessionInfo = new UserSessionInfo();

        userSessionInfo.setId(rs.getInt("id"));
        userSessionInfo.setUserId(rs.getInt("user_id"));
        userSessionInfo.setTime(rs.getLong("time"));
        userSessionInfo.setIp(rs.getString("ip"));

        return userSessionInfo;
    }
}
