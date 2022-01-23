package com.humaoyang.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.humaoyang.eduservice.bean.EduSubject;
import com.humaoyang.eduservice.bean.excel.SubjectData;
import com.humaoyang.eduservice.service.EduSubjectService;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService eduSubjectService;
    public SubjectExcelListener( EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if(data==null){
            throw new CustomizedException(20001,"文件数据为空");
        }
        EduSubject existOneSubject = existOneSubject(data.getOneSubject());
        if(existOneSubject==null){
            existOneSubject=new EduSubject();
            existOneSubject.setTitle(data.getOneSubject());
            eduSubjectService.save(existOneSubject);
        }
        String pid=existOneSubject.getId();
        EduSubject existTwoSubject = existTwoSubject(data.getTwoSubject(), pid);
        if(existTwoSubject==null){
            existTwoSubject=new EduSubject();
            existTwoSubject.setTitle(data.getTwoSubject());
            existTwoSubject.setParentId(pid);
            eduSubjectService.save(existTwoSubject);
        }


    }
    private EduSubject existOneSubject(String title){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title).eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }
    private EduSubject existTwoSubject(String title,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title).eq("parent_id",pid);
        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
