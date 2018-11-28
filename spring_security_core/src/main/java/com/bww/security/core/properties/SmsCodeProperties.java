package com.bww.security.core.properties;

/**
 * @ClassName SmsCodeProperties
 * @Description 短信验证码 也可以作为通用验证码
 * @Author yanran
 * @Date 2018/11/27 19:25
 * @Version 1.0
 **/

public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }
}
