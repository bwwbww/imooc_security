package com.bww.security.core.validate.code.sms;

import com.bww.security.core.properties.SecurityConstants;
import com.bww.security.core.validate.code.AbstractValidateCodeProcessor;
import com.bww.security.core.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @ClassName SmsCodeProcessor
 * @Description TODO
 * @Author yanran
 * @Date 2018/11/28 15:07
 * @Version 1.0
 **/
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws IOException, ServletRequestBindingException {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
