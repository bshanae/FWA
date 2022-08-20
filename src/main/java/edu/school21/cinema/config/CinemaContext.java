package edu.school21.cinema.config;

import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("edu.school21.cinema")
public class CinemaContext {
    @Bean
    public UserService userService() {
        return new UserServiceImplementation();
    }
}
