package com.MySpring;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@Component
@Order(-2147483648) //чтобы добавить фильтр первым.
public class FilterAPI implements Filter {

    private final static Logger logger = Logger.getLogger(FilterAPI.class.getName());

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info("Api request date - "+ new Date() + " path - " + req.getRequestURI() + " client IP address - " + req.getRemoteAddr());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

