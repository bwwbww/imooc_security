package com.bww.security.browser;

import com.bww.security.browser.authentication.MyAuthenticationFailureHandler;
import com.bww.security.browser.authentication.MyAuthenticationSuccessHandler;
import com.bww.security.core.AbstractChannelSecurityConfig;
import com.bww.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.bww.security.core.properties.SecurityConstants;
import com.bww.security.core.properties.SecurityProperties;
import com.bww.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //先配置TokenRepository
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
//                .httpBasic()
                //添加过滤器
//                .addFilterBefore(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
              //下面都封装在AbstractChannelSecurityConfig中
               /* .formLogin()
                注意  对该页面应该避免认证 否则会一直重定向到该页面进行认证
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                登录成功的默认处理可以被修改
                .successHandler(myAuthenticationSuccessHandler)
                登录失败的默认处理可以被修改
                .failureHandler(myAuthenticationFailureHandler)*/
                //下面是rememberMe的配置--浏览器特有的
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
        ;

    }
}
