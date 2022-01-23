package com.humaoyang.servicevod.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.servicevod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 阿里云视频点播服务
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;
    /**
     * 上传视频至阿里云
     */
    @PostMapping(value = "/uploadAlyiVideo")
    public R addVideo(MultipartFile file){
        String videoId=vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    /**
     * 移除阿里云视频
     * @param id 视频id
     */
    @DeleteMapping(value = "/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable(value = "id") String id){
        vodService.removeVideo(id);
        return R.ok();
    }
    /**
     * 批量移除阿里云视频
     */
    @DeleteMapping(value = "/delete-batch")
    public R deleteBatch(@RequestParam(value = "videoIdList") List<String> videoIdList){
        vodService.removeBatchVideo(videoIdList);
        return R.ok();
    }
    /**
     * 根据视频id获取播放凭证
     */
    @GetMapping(value = "getPlayAuth/{vid}")
    public R getPlayAuth(@PathVariable(value = "vid")String vid){
        String playAuth=vodService.getPlayAuth(vid);
        return R.ok().data("playAuth",playAuth);
    }
}
