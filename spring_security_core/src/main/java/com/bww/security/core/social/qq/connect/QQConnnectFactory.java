package com.bww.security.core.social.qq.connect;

import com.bww.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @ClassName QQConnnectFactory
 * @Description 连接工厂
 * @Author yanran
 * @Date 2018/12/3 14:29
 * @Version 1.0
 **/

public class QQConnnectFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnnectFactory(String providerId,String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
