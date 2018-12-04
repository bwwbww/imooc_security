package com.bww.security.core.social.qq.connect;

import com.bww.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @ClassName QQAdapter
 * @Description api适配器
 * @Author yanran
 * @Date 2018/12/3 14:14
 * @Version 1.0
 **/

//适配的api的类型
public class QQAdapter implements ApiAdapter<QQ> {

   //测试QQ的服务是不是可以使用
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        connectionValues.setDisplayName(qq.getUserInfo().getNickname());
        //40*40的图片  所有用户都有
        connectionValues.setImageUrl(qq.getUserInfo().getFigureurl_qq_1());
        //个人主页
        connectionValues.setProfileUrl(null);
        //用户在服务商出的唯一标识
        connectionValues.setProviderUserId(qq.getUserInfo().getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
