package edu.school21.cinema.config;

import edu.school21.cinema.services.UserService;
import edu.school21.cinema.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("edu.school21.cinema")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class CinemaContext {

    private final Environment env;

    @Autowired
    public CinemaContext(Environment env) {
        this.env = env;
    }

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
    public String imgSaveUrl() {return "image.url";}

    @Bean
    public UserService userService() {
        return new UserServiceImplementation();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("application.driver"));
        dataSource.setUrl(env.getRequiredProperty("application.url"));
        dataSource.setUsername(env.getRequiredProperty("application.username"));
        dataSource.setPassword(env.getRequiredProperty("application.password"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
