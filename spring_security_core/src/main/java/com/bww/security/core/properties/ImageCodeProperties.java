package com.bww.security.core.properties;

//在core中 是默认级别的配置
//这是图形验证码

public class ImageCodeProperties extends SmsCodeProperties {
    //默认的参数值
    private int width = 67;

    private int height = 23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
