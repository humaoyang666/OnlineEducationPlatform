package com.humaoyang.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.EduTeacher;
import com.humaoyang.eduservice.bean.vo.TeacherQuery;
import com.humaoyang.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "讲师管理模块")
@RestController
@RequestMapping(value = "/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @ApiOperation(value = "查询所有讲师")
    @GetMapping(value = "/findAll")
    public R getAll(){
        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
        return R.ok().data("items",eduTeacherList);
    }
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping(value = "/{id}")
    public R deleteTeacherById(@ApiParam(value = "要删除的讲师id") @PathVariable(value = "id") String id){
        boolean res = eduTeacherService.removeById(id);
        return res?R.ok():R.fail();
    }
    @ApiOperation(value = "分页查询讲师")
    @GetMapping(value = "/pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable(value = "current") Integer current,
                         @PathVariable(value = "limit") Integer limit){
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("rows",records).data("total",total);
    }
    @ApiOperation(value = "条件分页查询")
    @PostMapping(value = "/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable(value = "current") Integer current,
                                  @PathVariable(value = "limit") Integer limit,
                                  @RequestBody TeacherQuery teacherQuery){
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        queryWrapper.like(StringUtils.hasLength(name),"name",name).
                eq(!ObjectUtils.isEmpty(level),"level",level).
                ge(StringUtils.hasLength(begin),"gmt_create",begin).
                le(StringUtils.hasLength(end),"gmt_modified",end);
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        eduTeacherService.page(pageTeacher, queryWrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("rows",records).data("total",total);
    }
    @ApiOperation(value = "添加讲师")
    @PostMapping(value = "/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        return save?R.ok():R.fail();
    }
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping(value = "/getTeacher/{id}")
    public R getTeacher(@PathVariable(value = "id") String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }
    @ApiOperation(value = "修改讲师")
    @PostMapping(value = "/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update= eduTeacherService.updateById(eduTeacher);
        return update?R.ok():R.fail();
    }
}
