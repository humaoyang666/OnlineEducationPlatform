package com.humaoyang.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.humaoyang.eduservice.bean.EduChapter;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DELL
* @description 针对表【edu_chapter(课程)】的数据库操作Mapper
* @createDate 2022-01-03 17:47:39
* @Entity com.humaoyang.eduservice.bean.EduChapter
*/
@Mapper
public interface EduChapterMapper extends BaseMapper<EduChapter> {

}




