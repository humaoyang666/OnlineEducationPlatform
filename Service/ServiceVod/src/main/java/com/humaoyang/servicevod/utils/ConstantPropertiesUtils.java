package com.humaoyang.servicevod.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * vod服务数据常量类
 * @author 胡茂洋
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.vod.file")
public class ConstantPropertiesUtils implements InitializingBean, Serializable {
    private String regionId;
    private String accessKeyId;
    private String accessKeySecret;
    public static String REGION_ID;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_ID=regionId;
        ACCESS_KEY_ID=accessKeyId;
        ACCESS_KEY_SECRET=accessKeySecret;
    }
}
