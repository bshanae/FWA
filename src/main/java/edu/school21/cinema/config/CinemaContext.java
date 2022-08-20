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
    public String jspSignIn() {
        return "/WEB-INF/jsp/sign-in.jsp";
    }

    @Bean
    public String jspSignUp() {
        return "/WEB-INF/jsp/sign-up.jsp";
    }

    @Bean
    public String jspProfile() {
        return "/WEB-INF/jsp/profile.jsp";
    }

    @Bean
    public String uriProfile() {
        return "/profile";
    }

    @Bean
    public UserService userService() {
        return new UserServiceImplementation();
    }
}
