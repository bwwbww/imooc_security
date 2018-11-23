package com.bww.config;

import com.bww.filter.MyFilter;
import com.bww.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    //extends WebMvcConfigurerAdapter 复写addInterceptors
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
//    }

//    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MyFilter filter = new MyFilter();
        registrationBean.setFilter(filter);
        List<String> list = new ArrayList<>();
        //可以指定在哪些URL上起作用
        list.add("/*");
        registrationBean.setUrlPatterns(list);
        return registrationBean;
    }
}
