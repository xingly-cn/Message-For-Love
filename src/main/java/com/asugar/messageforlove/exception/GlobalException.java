package com.asugar.messageforlove.exception;


import com.asugar.messageforlove.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalException  {

    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> error(Exception e) {
        e.printStackTrace();
        return Result.error(500,"全局异常：" + e.getMessage());
    }

}
