package org.example.SpringbootWeb.aop;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.SpringbootWeb.mapper.OperateLogMapper;
import org.example.SpringbootWeb.pojo.OperateLog;
import org.example.SpringbootWeb.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(org.example.SpringbootWeb.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始前的日志
        // 操作人id - 请求头中的token
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");
        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();
        // 方法名
        String methodName = joinPoint.getSignature().getName();
        // 参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        // 调用原始目标方法, 前后包裹计时器
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        // 记录结束后的日志
        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);
        // 操作耗时
        Long costTime = endTime - startTime;
        // 调用目标方法
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insertOperateLog(operateLog);

        log.info("AOP 操作日志：{}", operateLog);
        return result;
    }
}
