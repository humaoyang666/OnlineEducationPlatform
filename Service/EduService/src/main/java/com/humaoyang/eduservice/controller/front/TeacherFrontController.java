package com.humaoyang.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.EduCourse;
import com.humaoyang.eduservice.bean.EduTeacher;
import com.humaoyang.eduservice.service.EduCourseService;
import com.humaoyang.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 前端讲师页面
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    EduTeacherService eduTeacherService;
    @Autowired
    EduCourseService eduCourseService;
    /**
     * 分页查询讲师
     * @return
     */
    @Cacheable(value = "teacherPage",key = "#page+'/'+#limit")
    @PostMapping(value = "/getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable(value = "page") Integer page, @PathVariable(value = "limit") Integer limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);
        Map<String,Object> map =eduTeacherService.getTeacherFrontList(eduTeacherPage);
        return R.ok().data(map);
    }
    /**
     * 获取讲师详情信息
     */
    @Cacheable(value = "teacherInfo",key = "#id")
    @GetMapping(value = "/getTeacherFrontInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable(value = "id") String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        if(ObjectUtils.isEmpty(id)){
            return R.ok();
        }
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("teacher_id",teacher.getId());
        List<EduCourse> eduCourseList = eduCourseService.list(courseQueryWrapper);
        return R.ok().data("teacher",teacher).data("courseList",eduCourseList);
    }
}
