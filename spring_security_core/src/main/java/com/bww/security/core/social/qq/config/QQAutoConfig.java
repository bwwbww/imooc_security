package com.bww.security.core.social.qq.config;

import com.bww.security.core.properties.QQProperties;
import com.bww.security.core.properties.SecurityProperties;
import com.bww.security.core.social.qq.connect.QQConnnectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @ClassName QQAutoConfig
 * @Description TODO
 * @Author yanran
 * @Date 2018/12/3 15:11
 * @Version 1.0
 **/
//ConditionalOnProperty 表示当配置文件中spring.security.social.qq被配置了值的时候才生效 否则下面不生效
@Configuration
@ConditionalOnProperty(prefix = "spring.security.social.qqProperties",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQqProperties();
        return new QQConnnectFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
