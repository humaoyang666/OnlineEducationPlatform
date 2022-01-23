package com.humaoyang.servicevod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.servicevod.service.VodService;
import com.humaoyang.servicevod.utils.ConstantPropertiesUtils;
import com.humaoyang.servicevod.utils.InitVod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 阿里云视频服务
 * @author 胡茂洋
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String title=fileName.substring(0,fileName.lastIndexOf("."));
        try( InputStream inputStream = file.getInputStream();) {
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId=response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizedException(ResultCode.FAILED, "视频上传失败");
        }

    }

    @Override
    public void removeVideo(String id) {
        if (!StringUtils.hasLength(id)) {
            return;
        }
        try {
            DefaultAcsClient client = InitVod.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(id);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomizedException(ResultCode.FAILED, "视频删除失败");
        }

    }

    @Override
    public void removeBatchVideo(List<String> videoIdList) {
        videoIdList=videoIdList.stream().filter(StringUtils::hasLength).collect(Collectors.toList());
        String ids=org.apache.commons.lang3.StringUtils.join(videoIdList,",");
        try {
            DefaultAcsClient client = InitVod.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(ids);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomizedException(ResultCode.FAILED, "视频删除失败");
        }
    }

    @Override
    public String getPlayAuth(String vid) {
        try {
            DefaultAcsClient client = InitVod.initVodClient();
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(vid);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            return response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomizedException(ResultCode.FAILED,"视频播放凭证获取失败");
        }

    }
}
