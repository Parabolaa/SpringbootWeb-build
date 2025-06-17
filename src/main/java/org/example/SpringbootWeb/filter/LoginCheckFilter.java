package org.example.SpringbootWeb.filter;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.SpringbootWeb.pojo.Result;
import org.example.SpringbootWeb.utils.JwtUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}",url);
        //判断url是否包含login
        if (url.contains("login")){
            log.info("登录操作，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //获取token
        String jwt = req.getHeader("token");
        //判断是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("token为空，返回未登录错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //解析，解析是否成功
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e){ // jwt解析失败
//            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
