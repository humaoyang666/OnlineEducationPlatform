package com.humaoyang.eduservice.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.eduservice.bean.EduVideo;
import com.humaoyang.eduservice.client.VodClient;
import com.humaoyang.eduservice.service.EduVideoService;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 课程小节视频cotroller
 * @author humaoyang
 */
@RestController
@RequestMapping(value = "/eduservice/video")
public class EduVideoController {
    @Autowired
    VodClient vodClient;
    @Autowired
    private EduVideoService eduVideoService;
    //添加小节
    @PostMapping(value = "/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }
    //删除小节
    @DeleteMapping(value = "{id}")
    public R deleteVideo(@PathVariable(value = "id") String id){
        EduVideo video = eduVideoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(StringUtils.hasLength(videoSourceId)){
            R r =  vodClient.removeAlyVideo(videoSourceId);
            if(!r.getSuccess()){
                throw new CustomizedException(ResultCode.FAILED,r.getMessage());
            }
        }

        eduVideoService.removeById(id);
        return R.ok();
    }
}
