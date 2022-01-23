package com.humaoyang.servicevod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
/**
 * 初始化视频点播对象
 * @author 胡茂洋
 */
public class InitVod {


    //填入AccessKey信息
    public static DefaultAcsClient initVodClient() throws ClientException {
        String accessKeyId=ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret=ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String regionId = ConstantPropertiesUtils.REGION_ID;  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
