package com.bww.security.browser;

import com.bww.security.browser.logOut.MyLogOutSuccessHandler;
import com.bww.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @ClassName BrowserSecurityBeanConfig
 * @Description TODO
 * @Author yanran
 * @Date 2018/12/4 19:13
 * @Version 1.0
 **/

@Configuration
public class BrowserSecurityBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler LogOutSuccessHandler() {
        return new MyLogOutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
    }
}
