package com.humaoyang.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.eduservice.bean.EduComment;
import com.humaoyang.eduservice.service.EduCommentService;
import com.humaoyang.eduservice.mapper.EduCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【edu_comment(评论)】的数据库操作Service实现
* @createDate 2022-01-15 11:07:35
*/
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment>
    implements EduCommentService{
    @Autowired
    EduCommentMapper eduCommentMapper;
}




