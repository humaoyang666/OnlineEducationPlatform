package com.humaoyang.eduservice.mapper;

import com.humaoyang.eduservice.bean.EduComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DELL
* @description 针对表【edu_comment(评论)】的数据库操作Mapper
* @createDate 2022-01-15 11:07:35
* @Entity com.humaoyang.eduservice.bean.EduComment
*/
@Mapper
public interface EduCommentMapper extends BaseMapper<EduComment> {

}




