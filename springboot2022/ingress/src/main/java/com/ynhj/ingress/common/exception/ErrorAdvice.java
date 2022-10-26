package com.ynhj.ingress.common.exception;

import com.ynhj.ingress.common.entity.GlobalException;
import com.ynhj.ingress.common.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @date: 2022/10/19
 * @author: yangniuhaojiang
 * @title: ErrorAdvice
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
@RestControllerAdvice
@Slf4j
public class ErrorAdvice {
    @ExceptionHandler(Exception.class)
    public Mono<R<Object>> handleCustomException(Exception e) {
        log.info("捕获到未处理的Exception异常：{}", e);
        return R.monoFail(500, e.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public Mono<R<Object>> handleBusinessException(GlobalException e) {
        log.info("捕获到BusinessException异常：{}", e);
        return R.monoFail(e.getStatus().value(), e.getMessage());
    }

}
