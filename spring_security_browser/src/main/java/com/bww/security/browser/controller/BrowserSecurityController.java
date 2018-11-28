package com.bww.security.browser.controller;

import com.bww.security.browser.support.SimpleResponse;
import com.bww.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@ResponseStatus(code= HttpStatus.UNAUTHORIZED)
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //可以缓存请求
    private RequestCache requestCache = new HttpSessionRequestCache();

    //跳转策略
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/authentication/require")
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request,response);
        System.out.println(savedRequest);
         if (savedRequest!=null){
             String redirectUrl = savedRequest.getRedirectUrl();
             logger.info("引发跳转的请求是："+redirectUrl);
             if (StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                 //跳转到登录页面上去 根据配置跳转相应的页面
                 redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
             }
         }
         return new SimpleResponse("需要身份验证，引导用户登录！");
    }
}
