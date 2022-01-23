package com.humaoyang.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.eduservice.bean.EduCourseDescription;
import com.humaoyang.eduservice.service.EduCourseDescriptionService;
import com.humaoyang.eduservice.mapper.EduCourseDescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【edu_course_description(课程简介)】的数据库操作Service实现
* @createDate 2022-01-03 17:43:07
*/
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription>
    implements EduCourseDescriptionService{
    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;
}




