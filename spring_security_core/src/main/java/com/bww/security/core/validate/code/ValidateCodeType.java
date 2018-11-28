package com.bww.security.core.validate.code;

import com.bww.security.core.properties.SecurityConstants;

/**
 * @ClassName ValidateCodeType
 * @Description 验证码类型   图形验证码  短信验证
 * @Author yanran
 * @Date 2018/11/27 10:53
 * @Version 1.0
 **/

public enum  ValidateCodeType {

    //短信验证码
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    //图形验证码
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };
    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
