package org.example.SpringbootWeb.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Arrays;

@Slf4j
//@Component
//@Aspect
public class TimeAspect {

    @Around("execution(* org.example.trysbweb..*.*(..))") // 切入点表达式
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 调用原始方法运行
        Object result = joinPoint.proceed();
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + "Time taken: {} ms", endTime - startTime);
        return result;
    }

@Around("execution(* org.example.trysbweb..*.*(..))") // 切入点表达式
public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    // 获得目标对象类名
    String className = joinPoint.getTarget().getClass().getName();
    log.info("目标对象类名: {}",className);
    // 获取目标对象方法名
    String methodName = joinPoint.getSignature().getName();
    log.info("目标对象方法名: {}",methodName);
    // 获取传入的参数
    Object[] args = joinPoint.getArgs();
    log.info("传入的参数: {}", Arrays.toString(args));
    // 放行
    Object result = joinPoint.proceed(args);
    // 获取返回值
    log.info("运行后的返回值: {}",result);
    return null;
}


}
