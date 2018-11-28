package com.bww.security.core.validate.code.sms;

import com.bww.security.core.properties.SecurityProperties;
import com.bww.security.core.validate.code.ValidateCode;
import com.bww.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName SmsValidateCodeGenerator
 * @Description 短信验证码生成器
 * @Author yanran
 * @Date 2018/11/27 19:20
 * @Version 1.0
 **/
@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode createCode(ServletWebRequest request) {
        String randomNumeric = RandomStringUtils.randomNumeric(securityProperties.getSms().getLength());
        return new ValidateCode(randomNumeric, securityProperties.getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
