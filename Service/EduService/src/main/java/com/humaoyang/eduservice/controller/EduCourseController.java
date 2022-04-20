package com.humaoyang.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.humaoyang.commonutils.CourseStatus;
import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.EduCourse;
import com.humaoyang.eduservice.bean.vo.CourseInfoVo;
import com.humaoyang.eduservice.bean.vo.CoursePublishVo;
import com.humaoyang.eduservice.bean.vo.CourseQuery;
import com.humaoyang.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程模块controller
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 添加课程信息
     * @param courseInfoVo 课程信息实体类
     * @return
     */
    @PostMapping(value = "/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据id查询课程信息
    @GetMapping(value = "/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable(value = "courseId") String courseId){
        CourseInfoVo courseInfoVo=eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    //修改课程信息
    @PostMapping(value = "/updateCourseInfo")
    public R updateCourseInfo(@RequestBody  CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    //获取发布课程总信息
    @GetMapping(value = "/getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable(value = "id") String id){
        CoursePublishVo coursePublishVo =eduCourseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }
    //课程最终发布修改状态
    @PostMapping(value = "/publishCourse/{id}")
    public R publishCourse(@PathVariable(value = "id") String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(CourseStatus.NORMAL);
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }
    //获取课程分页信息
    @PostMapping(value = "/pageCourseCondition/{current}/{limit}")
    @ApiOperation(value = "获取课程分页列表")
    public R getCourseListPage(@PathVariable(value = "current") Integer current,
                               @PathVariable(value = "limit") Integer limit,
                               @RequestBody CourseQuery courseQuery){
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        Page<EduCourse> eduCoursePage = new Page<>(current,limit);
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        eduCourseQueryWrapper.eq(StringUtils.hasLength(status),"status",status).
                like(StringUtils.hasLength(title),"title",title);
        eduCourseService.page(eduCoursePage,eduCourseQueryWrapper);
        List<EduCourse> records = eduCoursePage.getRecords();
        long total = eduCoursePage.getTotal();
        return R.ok().data("rows",records).data("total",total);
    }
    @DeleteMapping(value = "/{courseId}")
    @ApiOperation(value = "删除课程")
    public R deleteCourse(@PathVariable(value = "courseId") String courseId){
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }

}
