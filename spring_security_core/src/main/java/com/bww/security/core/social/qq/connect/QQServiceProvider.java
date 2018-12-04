package com.bww.security.core.social.qq.connect;

import com.bww.security.core.social.qq.api.QQ;
import com.bww.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @ClassName QQServiceProvider
 * @Description 服务提供商完成
 * @Author yanran
 * @Date 2018/12/3 13:57
 * @Version 1.0
 **/
//api接口的类型QQ
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final  String URL_AUTHORIZE="https://graph.qq.com/oauth2.0/authorize";
    private static final  String URL_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        //使用自己的QQOAuth2Template
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
