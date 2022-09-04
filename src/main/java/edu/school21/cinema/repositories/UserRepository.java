package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean createUser(String firstName, String lastName, String phone, String password) {
          return jdbcTemplate.update("INSERT INTO users (first_name, last_name, phone_number, password)" +
                " VALUES(?, ?, ?, ?)", firstName, lastName, phone, password) > 0;
    }

    public User findUserByPhone(String phone) {
            User user = jdbcTemplate.query("SELECT * FROM users where phone_number=?", new BeanPropertyRowMapper<>(User.class), phone)
                    .stream().findAny().orElse(null);
            return user;
    }

    public void update(String phone, User updatedUser) {
        jdbcTemplate.update("UPDATE users SET first_name=?, last_name=?, phone_number=?, password=?", updatedUser.getFirstName(),
                updatedUser.getLastName(), updatedUser.getPhoneNumber(), updatedUser.getPassword());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users where id=?", id);
    }

    public List<User> getAllUsers(String phone) {
        return jdbcTemplate.query("SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserByToken(String token) {
        // Current token is the user's phone number
        return findUserByPhone(token);
    }
}
