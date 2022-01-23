package com.humaoyang.eduservice.service;

import com.humaoyang.eduservice.bean.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.humaoyang.eduservice.bean.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author DELL
* @description 针对表【edu_subject(课程科目)】的数据库操作Service
* @createDate 2022-01-02 16:38:45
*/
public interface EduSubjectService extends IService<EduSubject> {

    boolean saveSubject(MultipartFile file);

    List<OneSubject> getSubjectTree();
}
