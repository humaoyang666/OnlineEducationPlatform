package com.humaoyang.eduservice.controller.front;

import com.humaoyang.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论功能controller
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduservice/commentfront")
public class CommentFrontController {
    /**
     * 获取评论分页信息
     */
    @Autowired
    EduCommentService eduCommentService;
}
