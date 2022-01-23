package com.humaoyang.servicemsm.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * oss服务数据常量类
 * @author 胡茂洋
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.msm")
public class ConstantPropertiesUtils implements InitializingBean {
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String templateCode;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String SIGNNAME;
    public static String TEMPLATECODE;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID=accessKeyId;
        ACCESS_KEY_SECRET=accessKeySecret;
        SIGNNAME=signName;
        TEMPLATECODE=templateCode;
    }
}
