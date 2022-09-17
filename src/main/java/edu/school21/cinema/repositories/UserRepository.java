package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean createUser(User user) {
        int result = jdbcTemplate.update(
                "INSERT INTO users (first_name, last_name, phone_number, password, email, img) VALUES(?, ?, ?, ?, ?, ?)",
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getEmail(),
                user.getImg());

        return result > 0;
    }

    public User findUserByPhone(String phone) {
        return jdbcTemplate.query(
                                   "SELECT * FROM users where phone_number=?",
                                   new BeanPropertyRowMapper<>(User.class),
                                   phone)
                           .stream()
                           .findAny()
                           .orElse(null);
    }

    public User findUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM users where id=?", new BeanPropertyRowMapper<>(User.class), id)
                           .stream()
                           .findAny()
                           .orElse(null);
    }

    public void update(User updatedUser) {
        jdbcTemplate.update(
                "UPDATE users SET first_name=?, last_name=?, phone_number=?, password=?, email=?, img=? where id=?",
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getPhoneNumber(),
                updatedUser.getPassword(),
                updatedUser.getEmail(),
                updatedUser.getImg(),
                updatedUser.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users where id=?", id);
    }

    public List<User> getAllUsers(String phone) {
        return jdbcTemplate.query("SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }

    public User findUserByToken(String token) {
        return findUserByPhone(token);
    }
}
