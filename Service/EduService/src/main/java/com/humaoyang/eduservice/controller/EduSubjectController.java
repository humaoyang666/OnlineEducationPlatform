package com.humaoyang.eduservice.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.subject.OneSubject;
import com.humaoyang.eduservice.service.EduSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程管理模块
 * @author 胡茂洋
 */
@Slf4j
@RestController
@RequestMapping(value = "/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    @PostMapping(value = "/addSubject")
    public R addSubject(MultipartFile file){
        boolean res=eduSubjectService.saveSubject(file);
        return res?R.ok():R.fail();
    }
    @GetMapping(value = "/getAllSubject")
    public R getAllSubject(){
        List<OneSubject> oneSubjectList=eduSubjectService.getSubjectTree();
        return R.ok().data("list",oneSubjectList);
    }
}
