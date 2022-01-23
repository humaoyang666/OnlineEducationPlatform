package com.humaoyang.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.humaoyang.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.humaoyang.eduservice.bean.frontvo.CourseQueryVo;
import com.humaoyang.eduservice.bean.frontvo.CourseWebVo;
import com.humaoyang.eduservice.bean.vo.CourseInfoVo;
import com.humaoyang.eduservice.bean.vo.CoursePublishVo;

import java.util.Map;

/**
* @author DELL
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-01-03 17:41:01
*/
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    void removeCourse(String courseId);

    /**
     * 课程分页查询前端
     * @param pageCourse 分页信息
     * @param courseQueryVo 课程查询条件
     * @return
     */
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseQueryVo courseQueryVo);

    /**
     * 前端获取课程详情信息
     * @param id 课程id
     * @return
     */
    CourseWebVo getWebCourseInfo(String id);
}
