package com.bww.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeProcessor
 * @Description 校验码生成器  用来生成不同的校验码
 * @Author yanran
 * @Date 2018/11/27 10:22
 * @Version 1.0
 **/
public interface ValidateCodeGenerator {
    //创建验证码
    ValidateCode createCode(ServletWebRequest request);
}
