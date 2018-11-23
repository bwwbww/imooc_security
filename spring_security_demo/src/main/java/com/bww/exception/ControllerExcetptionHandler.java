package com.bww.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

//controller异常信息处理
@ControllerAdvice  //拦截异常同一处理
public class ControllerExcetptionHandler {

    @ExceptionHandler(MyRuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerException(MyRuntimeException ex){
        Map<String,Object> res = new HashMap<>();
        res.put("id",ex.getId());
        res.put("message",ex.getMessage());
        return res;
    }
}
