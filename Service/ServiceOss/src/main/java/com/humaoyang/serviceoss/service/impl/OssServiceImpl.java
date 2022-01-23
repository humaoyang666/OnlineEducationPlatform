package com.humaoyang.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.humaoyang.serviceoss.service.OssService;
import com.humaoyang.serviceoss.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传文件至阿里云oss
     * @param multipartFile
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile multipartFile) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName=ConstantPropertiesUtils.BUCKET_NAME;
        String uuid= UUID.randomUUID().toString().replaceAll("-","");
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String filename = multipartFile.getOriginalFilename();
        filename=uuid+filename;
        try (InputStream inputStream = multipartFile.getInputStream();){
            ossClient.putObject(bucketName,filename,inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            if(ossClient!=null){
                ossClient.shutdown();
            }
        }
        String url="https://"+bucketName+"."+endpoint+"/"+filename;
        return url;
    }
}
