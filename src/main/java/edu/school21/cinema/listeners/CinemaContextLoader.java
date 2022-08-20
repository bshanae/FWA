package edu.school21.cinema.listeners;

import edu.school21.cinema.config.CinemaContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CinemaContextLoader implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        ApplicationContext cinemaContext = new AnnotationConfigApplicationContext(CinemaContext.class);

        servletContext.setAttribute("context", cinemaContext);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
