package edu.school21.cinema.filters;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import java.io.IOException;

public abstract class CinemaFilter implements Filter {
    public void init(FilterConfig filterConfig) {
        ApplicationContext applicationContext = (ApplicationContext) filterConfig
                .getServletContext()
                .getAttribute("context");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        beanFactory.autowireBean(this);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    }

    public void destroy() {
    }
}
