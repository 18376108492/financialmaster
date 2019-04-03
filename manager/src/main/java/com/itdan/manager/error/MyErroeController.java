package com.itdan.manager.error;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理类
 */

public class MyErroeController extends BasicErrorController {

    public MyErroeController(ErrorAttributes errorAttributes,
                             ErrorProperties errorProperties,
                             List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        Map<String, Object> attrs=super.getErrorAttributes(request, includeStackTrace);
        /**
         * 错误信息
         {
         "timestamp": "2019-04-03T13:24:00.606+0000",
         "status": 500,
         "error": "Internal Server Error",
         "message": "No message available",
         "path": "/manager/products"
         }
          */
        attrs.remove("timestamp");
        attrs.remove("status");
        attrs.remove("error");
        attrs.remove("path");
        return attrs;
    }
}
