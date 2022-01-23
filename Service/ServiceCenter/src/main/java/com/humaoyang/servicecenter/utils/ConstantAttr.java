package com.humaoyang.servicecenter.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 微信登陆开发配置文件常量
 *@author 胡茂洋
 */
@Component
@ConfigurationProperties(value = "wx.open")
@Data
public class ConstantAttr implements Serializable, InitializingBean {
    private String appId;
    private String appSecret;
    private String redirectUrl;
    public static String APPID;
    public static String APPSECRET;
    public static String REDIRECTURL;
    @Override
    public void afterPropertiesSet() throws Exception {
        APPID=appId;
        APPSECRET=appSecret;
        REDIRECTURL=redirectUrl;
    }
}
