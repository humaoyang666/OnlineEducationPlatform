package com.humaoyang.eduservice.mapper;

import com.humaoyang.eduservice.bean.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DELL
* @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
* @createDate 2022-01-02 16:38:45
* @Entity com.humaoyang.eduservice.bean.EduSubject
*/
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

}




