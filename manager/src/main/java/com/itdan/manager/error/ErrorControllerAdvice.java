package com.itdan.manager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理(第二种处理方式)
 */
@ControllerAdvice(basePackages = {"com.itdan.manager.controller"})
public class ErrorControllerAdvice {

    /**
     * 异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
   public ResponseEntity handlerException(Exception e){
        Map<String, Object> attrs=new HashMap<>();
        String errorCode = (String) attrs.get("message");
        ErrorEnum errorEnum = ErrorEnum.getErroeByCode(errorCode);
        attrs.put("message",errorEnum.getMessage());
        attrs.put("code",errorEnum.getCode());
        attrs.put("canRetry",errorEnum.isCanRetry());
        return new ResponseEntity(attrs,HttpStatus.INTERNAL_SERVER_ERROR);
   }

}
