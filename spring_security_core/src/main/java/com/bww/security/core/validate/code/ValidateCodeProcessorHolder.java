package com.bww.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName ValidateCodeProcessorHolder
 * @Description TODO
 * @Author yanran
 * @Date 2018/11/27 17:23
 * @Version 1.0
 **/
@Component
public class ValidateCodeProcessorHolder {

    //依赖查找
    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        String typ = type.toString().toLowerCase();
        return findValidateCodeProcessor(typ);
    }
    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
