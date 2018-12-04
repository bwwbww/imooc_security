package com.bww.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @ClassName QQProperties
 * @Description TODO
 * @Author yanran
 * @Date 2018/12/3 15:00
 * @Version 1.0
 **/

public class QQProperties extends SocialProperties {
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
