package com.bww.security.core.validate.code;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeProcessor
 * @Description 校验码处理器  用来封装不同校验码的处理逻辑
 * @Author yanran
 * @Date 2018/11/27 10:22
 * @Version 1.0
 **/

public interface ValidateCodeProcessor {
    //验证码放入session的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /*
     * @Author yanran
     * @Description 构建验证码  ServletWebRequest封装了request response
     * @Date 10:25 2018/11/27
     * @Param [request]
     * @return void
     **/
    void create(ServletWebRequest request) throws Exception;

    /*
     * @Author yanran
     * @Description
     * @Date 10:26 2018/11/27
     * @Param []
     * @return void
     **/
    void validateCode(ServletWebRequest request) throws ServletRequestBindingException;
}
