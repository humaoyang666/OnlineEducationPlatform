package com.humaoyang.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.humaoyang.eduservice.bean.EduTeacher;

import java.util.Map;

/**
* @author DELL
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2021-12-28 15:12:54
*/
public interface EduTeacherService extends IService<EduTeacher> {


    Map<String, Object> getTeacherFrontList(Page<EduTeacher> eduTeacherPage);
}
