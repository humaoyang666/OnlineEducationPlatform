package com.humaoyang.serviceoss.utils;

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
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class ConstantPropertiesUtils implements InitializingBean {
    private String endPoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endPoint;
        ACCESS_KEY_ID=accessKeyId;
        ACCESS_KEY_SECRET=accessKeySecret;
        BUCKET_NAME=bucketName;
    }
}
