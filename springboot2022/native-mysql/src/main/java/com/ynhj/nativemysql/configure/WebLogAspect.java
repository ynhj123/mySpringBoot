package com.ynhj.nativemysql.configure;

import com.alibaba.fastjson2.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2021-05-14
 * @author: yangniuhaojiang
 * @title: WebController
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */

@Aspect
@Component
@Slf4j
public class WebLogAspect {
    public WebLogAspect() {
    }

    /**
     * 定义请求日志切入点，其切入点表达式有多种匹配方式,这里是指定路径
     */
    @Pointcut("execution(public * com.ynhj.nativemysql.controller.*.*(..))")
    public void webLogPointcut() {
    }


    @Around("webLogPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        if (result instanceof Mono) {
            var monoResult = (Mono<?>) result;
            return monoResult.doOnSuccess(o -> {
                RequestInfo requestInfo = ReactiveRequestContextHolder.getInstance().get();
                requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                        proceedingJoinPoint.getSignature().getName()));
                requestInfo.setRequestParams(getRequestParamsByProceedingJoinPoint(proceedingJoinPoint));
                requestInfo.setResult(o);
                requestInfo.setTimeCost(System.currentTimeMillis() - start);
                log.info("Request Info: {}", JSON.toJSONString(requestInfo));
            });
        }
        return result;
    }

    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 1. 在异常通知中设置异常信息，并将其保存
     *
     * @param throwable
     */
    @AfterThrowing(value = "webLogPointcut()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, RuntimeException throwable) {
        // 保存异常日志记录
        RequestInfo request = ReactiveRequestContextHolder.getInstance().get();
        RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
        requestErrorInfo.setIp(request.getIp());
        requestErrorInfo.setUrl(request.getUrl());
        requestErrorInfo.setHttpMethod(request.getHttpMethod());
        requestErrorInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName()));
        requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        requestErrorInfo.setException(throwable);
        log.error("Error Request Info: {}", JSON.toJSONString(requestErrorInfo));
    }

    @Data
    public static class RequestInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private Object result;
        private Long timeCost;
    }

    @Data
    public class RequestErrorInfo {
        private String ip;
        private String url;
        private String httpMethod;
        private String classMethod;
        private Object requestParams;
        private RuntimeException exception;
    }

    private Map<String, Object> getRequestParamsByProceedingJoinPoint(ProceedingJoinPoint proceedingJoinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        //参数名
        String[] paramNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

        return buildRequestParam(paramNames, paramValues);
    }

    private Map<String, Object> buildRequestParam(String[] paramNames, Object[] paramValues) {
        Map<String, Object> requestParams = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }
}
