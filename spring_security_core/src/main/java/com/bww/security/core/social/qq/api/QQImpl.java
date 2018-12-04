package com.bww.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @ClassName QQImpl
 * @Description 获取用户的信息
 * AbstractOAuth2ApiBinding  accessToken(令牌)  restTemplate（发送http请求）
 * @Author yanran
 * @Date 2018/12/3 13:21
 * @Version 1.0
 **/
//accessToken父类AbstractOAuth2ApiBinding提供
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    //OpenID是此网站上或应用中唯一对应用户身份的标识，网站或应用可将此ID进行存储，便于用户下次登录时辨识其身份，
    // 或将其与用户在网站上或应用中的原有账号进行绑定。
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    //应用的唯一标识。在OAuth2.0认证过程中，appid的值即为oauth_consumer_key的值。
    private String appId;

    //根据 Access Token，得到对应用户身份的OpenID
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        //ACCESS_TOKEN_PARAMETER 此策略是说将accessToken作为参数传递 默认是放在请求头中的
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败！", e);
        }
    }
}
