package com.bww.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @ClassName MySpringSocialConfigurer
 * @Description TODO
 * @Author yanran
 * @Date 2018/12/4 10:05
 * @Version 1.0
 **/

public class MySpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public MySpringSocialConfigurer(String filterProcessesUrl){
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
