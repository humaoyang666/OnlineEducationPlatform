package com.humaoyang.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.EduCourse;
import com.humaoyang.eduservice.bean.EduTeacher;
import com.humaoyang.eduservice.service.EduCourseService;
import com.humaoyang.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前端名师查询和热门课程接口
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduservice/indexfront")
public class IndexFrontController {
    @Autowired
    EduTeacherService eduTeacherService;
    @Autowired
    EduCourseService eduCourseService;

    @Cacheable(value = "index",key = "'indexList'")
    @GetMapping(value = "/index")
    public R index(){
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("level").last("limit 4");
        courseQueryWrapper.orderByDesc("view_count").last("limit 8");
        List<EduTeacher> eduTeacherList = eduTeacherService.list(teacherQueryWrapper);
        List<EduCourse> eduCourseList = eduCourseService.list(courseQueryWrapper);
        return R.ok().data("eduList",eduCourseList).data("teacherList",eduTeacherList);
    }
}
