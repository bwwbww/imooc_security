package com.bww.security.core.properties;

/**
 * @ClassName SocialProperties
 * @Description TODO
 * @Author yanran
 * @Date 2018/12/3 15:05
 * @Version 1.0
 **/

public class SocialProperties {
    private QQProperties qqProperties = new QQProperties();

    private String filterProcessUrl = "/auth";

    public QQProperties getQqProperties() {
        return qqProperties;
    }

    public void setQqProperties(QQProperties qqProperties) {
        this.qqProperties = qqProperties;
    }

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }
}
