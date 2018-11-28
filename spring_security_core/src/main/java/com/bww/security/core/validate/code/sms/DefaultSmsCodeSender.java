package com.bww.security.core.validate.code.sms;

import org.springframework.stereotype.Component;

/**
 * @ClassName DefaultSmsCodeSender
 * @Description TODO
 * @Author yanran
 * @Date 2018/11/28 15:15
 * @Version 1.0
 **/
@Component
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
