package org.example.SpringbootWeb.filter;

import jakarta.servlet.*;

import java.io.IOException;


//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override // 初始化，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("DemoFilter init");
    }

    @Override // 拦截到请求后调用，调用多次
    public void destroy() {
//        Filter.super.destroy();
        System.out.println("DemoFilter destroy");
    }

    @Override // 销毁，只调用一次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("DemoFilter doFilter, before let go"); //如果只有这句，就是没有放行
        // 等于说在dofilter里面执行了所有web请求
        // 默认是放行到下一个filter，如果没有filter了就放行到web资源，访问
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("DemoFilter doFilter, after let go");
    }
}
