package com.humaoyang.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.eduservice.bean.EduSubject;
import com.humaoyang.eduservice.bean.excel.SubjectData;
import com.humaoyang.eduservice.bean.subject.OneSubject;
import com.humaoyang.eduservice.bean.subject.TwoSubject;
import com.humaoyang.eduservice.listener.SubjectExcelListener;
import com.humaoyang.eduservice.mapper.EduSubjectMapper;
import com.humaoyang.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author DELL
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-01-02 16:38:45
*/
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
    implements EduSubjectService{
    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Override
    public boolean saveSubject(MultipartFile file) {
        if(file.isEmpty()){
            return false;
        }
        try(InputStream is=file.getInputStream();) {
            EasyExcel.read(is, SubjectData.class,new SubjectExcelListener(this)).sheet(0).doRead();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<OneSubject> getSubjectTree() {
        List<OneSubject> res=new ArrayList<>();
        QueryWrapper<EduSubject> oneSubjectWrapper = new QueryWrapper<>();
        oneSubjectWrapper.eq("parent_id","0");
        QueryWrapper<EduSubject> twoSubjectWrapper = new QueryWrapper<>();
        twoSubjectWrapper.ne("parent_id","0");
        List<EduSubject> fatherList = eduSubjectMapper.selectList(oneSubjectWrapper);
        List<EduSubject> childrenList = eduSubjectMapper.selectList(twoSubjectWrapper);
        for (EduSubject e:fatherList){
            OneSubject root = new OneSubject();
            root.setId(e.getId());
            root.setTitle(e.getTitle());
            res.add(root);
            for (EduSubject child : childrenList) {
                if(Objects.equals(child.getParentId(),root.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(child,twoSubject);
                    root.getChildren().add(twoSubject);
                }
            }
        }
        return res;
    }
}




