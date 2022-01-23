package com.humaoyang.serviceorder.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 微信支付常量类
 * @author 胡茂洋
 */
@Data
@Component
@ConfigurationProperties(value = "weixin.pay")
public class WeChatPayProperties implements Serializable, InitializingBean {
    //appid
    @ApiModelProperty(value = "appid")
    private String appId;
    @ApiModelProperty(value = "商户id")
    private String mchId;
    @ApiModelProperty(value = "商户密钥")
    private String mchKey;
    @ApiModelProperty(value = "回调地址")
    private String notifyurl;
    public static String APPID;
    public static String MCHID;
    public static String MCHKEY;
    public static String NOTIFYURL;
    @Override
    public void afterPropertiesSet() throws Exception {
        APPID=appId;
        MCHID=mchId;
        MCHKEY=mchKey;
        NOTIFYURL=notifyurl;
    }
}
