package com.humaoyang.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.eduservice.bean.EduTeacher;
import com.humaoyang.eduservice.mapper.EduTeacherMapper;
import com.humaoyang.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
* @createDate 2021-12-28 15:12:54
*/
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
    implements EduTeacherService{
    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public Map<String, Object> getTeacherFrontList(Page<EduTeacher> eduTeacherPage) {
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("sort");
        eduTeacherMapper.selectPage(eduTeacherPage,teacherQueryWrapper);
        long current = eduTeacherPage.getCurrent();
        long pages = eduTeacherPage.getPages();
        long size = eduTeacherPage.getSize();
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        boolean hasNext = eduTeacherPage.hasNext();//上一页
        boolean hasPrevious = eduTeacherPage.hasPrevious();//下一页
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }
}




