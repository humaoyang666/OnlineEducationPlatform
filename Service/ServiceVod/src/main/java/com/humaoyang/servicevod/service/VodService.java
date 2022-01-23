package com.humaoyang.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 胡茂洋
 */
public interface VodService{
    /**
     * 上传文件至阿里云服务器
     * @param file 客户端上传的视频
     * @return 上传视频id
     */
    String uploadVideo(MultipartFile file);

    /**
     * 删除阿里云视频
     * @param id 视频id
     */
    void removeVideo(String id);

    void removeBatchVideo(List<String> videoIdList);

    /**
     * 根据视频id获取视频播放凭证
     * @param vid
     * @return
     */
    String getPlayAuth(String vid);
}
