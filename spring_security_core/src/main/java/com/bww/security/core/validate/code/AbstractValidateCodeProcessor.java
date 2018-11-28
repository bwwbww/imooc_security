package com.bww.security.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName AbstractValidateCodeProcessor
 * @Description TODO
 * @Author yanran
 * @Date 2018/11/27 10:31
 * @Version 1.0
 **/

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    /*
     * @Author yanran
     * @Description //操作session的工具
     * @Date 10:36 2018/11/27
     * @Param
     * @return
     **/
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //收集所有的 {@link ValidateCodeGenerator} 接口的实现。--依赖搜索
    //依赖查找--这是在系统启动的时候，会查找ValidateCodeGenerator接口的实现类，
    // 以他的名字为key，存在map中
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        //1生成验证码
        C validateCode = generate(request);
        //2保存到session中
        save(request, validateCode);
        //3发送
        send(request, validateCode);
    }

    private C generate(ServletWebRequest request) {
        ValidateCodeType validateCodeType = getValidateCodeType(request);
        String generatorName = validateCodeType.toString().toLowerCase() + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (C) validateCodeGenerator.createCode(request);
    }

    //保存验证码
    private void save(ServletWebRequest request, C validateCode) {
        String sessionKey = getSessionKey(request);
        sessionStrategy.setAttribute(request, sessionKey, validateCode);
    }

    //发送验证码 由子类实现
    protected abstract void send(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;

    //获取 session的key
    private String getSessionKey(ServletWebRequest request) {
        return SESSION_KEY_PREFIX + getValidateCodeType(request).toString().toUpperCase();
    }

    //根据请求的url获取校验码的类型
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @Override
    public void validateCode(ServletWebRequest request) {
        ValidateCodeType type = getValidateCodeType(request);
        String sessionKey = getSessionKey(request);
        C codeInSession =  (C)sessionStrategy.getAttribute(request, sessionKey);
        //表单填写的验证码
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), type.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, sessionKey);
    }
}
