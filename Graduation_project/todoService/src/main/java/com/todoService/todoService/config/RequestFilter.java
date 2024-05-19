package com.todoService.todoService.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String token = httpServletRequest.getHeader(RequestContext.REQUEST_HEADER_NAME);
//        if(token == null || "".equals(token)){
//            throw new IllegalArgumentException("Can't retrieve JWT Token!!");
//        }
//        RequestContext.getContext().setToken(token);
//        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
