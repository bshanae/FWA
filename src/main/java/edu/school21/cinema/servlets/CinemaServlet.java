package edu.school21.cinema.servlets;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class CinemaServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();

        ApplicationContext applicationContext = (ApplicationContext) getServletContext().getAttribute("context");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        beanFactory.autowireBean(this);
    }
}
