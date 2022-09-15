package edu.school21.cinema.filters;

import edu.school21.cinema.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebFilter(urlPatterns = {"/sign-in", "/sign-up"}, filterName = "nonAuthorizedFilter")
@WebFilter(urlPatterns = {"/sign-in"}, filterName = "nonAuthorizedFilter")
public class NonAuthorizedFilter extends CinemaFilter {
    @Autowired
    @Qualifier("uriProfile")
    private String uriProfile;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute(SessionKey.TOKEN) != null) {
            httpResponse.sendRedirect(uriProfile);
            return;
        }

        chain.doFilter(request, response);
    }
}
