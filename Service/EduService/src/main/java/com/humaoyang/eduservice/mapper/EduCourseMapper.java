package com.humaoyang.eduservice.mapper;

import com.humaoyang.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.humaoyang.eduservice.bean.frontvo.CourseWebVo;
import com.humaoyang.eduservice.bean.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author DELL
* @description 针对表【edu_course(课程)】的数据库操作Mapper
* @createDate 2022-01-03 17:41:01
* @Entity com.humaoyang.eduservice.bean.EduCourse
*/
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 获取课程与描述表，教师表，分类表的总信息
     * @param id 课程id
     * @return 课程发布总信息
     */
    public CoursePublishVo getCoursePublishVo(@Param(value = "id") String id);

    CourseWebVo getWebCourseInfoById(@Param(value = "id") String id);
}




