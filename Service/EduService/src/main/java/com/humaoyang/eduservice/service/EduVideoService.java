package com.humaoyang.eduservice.service;

import com.humaoyang.eduservice.bean.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DELL
* @description 针对表【edu_video(课程视频)】的数据库操作Service
* @createDate 2022-01-03 17:50:27
*/
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);
}
