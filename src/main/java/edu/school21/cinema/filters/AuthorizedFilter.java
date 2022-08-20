package edu.school21.cinema.filters;

import edu.school21.cinema.SessionKey;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/profile"}, filterName = "authorizedFilter")
public class AuthorizedFilter extends CinemaFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute(SessionKey.TOKEN) == null) {
            httpResponse.sendError(403);
            return;
        }

        chain.doFilter(request, response);
    }
}
