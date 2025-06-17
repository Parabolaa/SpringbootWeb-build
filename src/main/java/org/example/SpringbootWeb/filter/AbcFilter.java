package org.example.SpringbootWeb.filter;

import jakarta.servlet.*;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class AbcFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("AbcFilter init");
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        System.out.println("AbcFilter destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AbcFilter doFilter, before let go");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("AbcFilter doFilter, after let go");
    }
}
