package com.humaoyang.servicemsm.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.humaoyang.commonutils.RandomUtil;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.servicemsm.service.MsmService;
import com.humaoyang.servicemsm.utils.ConstantPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 阿里云短信服务
 * @author 胡茂洋
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public void sendMsm(String phone) {
        if(!StringUtils.hasLength(phone)){
            throw new CustomizedException(ResultCode.FAILED,"请输入正确手机号");
        }
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.hasLength(code)){
            return;
        }
        code = RandomUtil.getFourBitRandom();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);//自己账号的AccessKey信息
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");//短信服务的服务接入地址
        request.setSysVersion("2017-05-25");//API的版本号
        request.setSysAction("SendSms");//API的名称
        request.putQueryParameter("PhoneNumbers", phone);//接收短信的手机号码
        request.putQueryParameter("SignName", ConstantPropertiesUtils.SIGNNAME);//短信签名名称
        request.putQueryParameter("TemplateCode", ConstantPropertiesUtils.TEMPLATECODE);//短信模板ID
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");//短信模板变量对应的实际值
        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            if(!success){
                throw new CustomizedException(ResultCode.FAILED,"短信发送失败");
            }
            redisTemplate.opsForValue().set(phone,code,10, TimeUnit.MINUTES);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
