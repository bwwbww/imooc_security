package com.bww.security.core.validate.code;

import com.bww.security.core.properties.SecurityProperties;
import com.bww.security.core.validate.code.image.ImageValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    //方法的名字就是放到spring容器里的bean的名字

    //简单的是在实现类上用component注解  这里为了使用ConditionalOnMissingBean注解
    //该注解可以先检查是不是已经存在这样的bean  如果存在就不用在创建bean了
    //作用  可扩展性强
    @Bean
    @ConditionalOnMissingBean(name="imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageCodeGenerate = new ImageValidateCodeGenerator();
        imageCodeGenerate.setSecurityProperties(securityProperties);
        return imageCodeGenerate;
    }
}
