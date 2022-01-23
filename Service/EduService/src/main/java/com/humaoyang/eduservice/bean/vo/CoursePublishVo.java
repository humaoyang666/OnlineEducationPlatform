package com.humaoyang.eduservice.bean.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 课程发布返回结果最终类
 * @author 胡茂洋
 */
@ApiModel(value = "课程发布返回实体类")
@Data
public class CoursePublishVo implements Serializable {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
