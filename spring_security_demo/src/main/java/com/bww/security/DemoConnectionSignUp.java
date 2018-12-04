package com.bww.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @ClassName DemoConnectionSignUp
 * @Description 通过此配置实现自动注册功能  不要用户感知
 * @Author yanran
 * @Date 2018/12/4 14:41
 * @Version 1.0
 **/
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //更具用户信息默认创建用户并返回用户唯一标识

        return connection.getDisplayName();
    }
}
