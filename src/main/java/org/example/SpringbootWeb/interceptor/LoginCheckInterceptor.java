package org.example.SpringbootWeb.interceptor;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.SpringbootWeb.pojo.Result;
import org.example.SpringbootWeb.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前运行，返回true放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        System.out.println("LoginCheckInterceptor preHandle");
        // 在这里登陆校验
        // 获取请求url
        String url = request.getRequestURL().toString();
        log.info("url: {}", url);
        //判断url是否包含login
        if (url.contains("login")){
            log.info("登录操作，放行");
            return true;
        }
        //获取token
        String jwt = request.getHeader("token");
        //判断是否存在
        if (!StringUtils.hasLength(jwt)) {
            log.info("token为空，返回未登录错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //解析，解析是否成功
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) { // jwt解析失败
//            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }
        //放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override // 目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("LoginCheckInterceptor postHandle");
    }

    @Override // 视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("LoginCheckInterceptor afterCompletion");
    }
}
