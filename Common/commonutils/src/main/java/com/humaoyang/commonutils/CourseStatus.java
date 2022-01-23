package com.humaoyang.commonutils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 课程状态码
 * @author 胡茂洋
 */
@ApiModel(value = "课程发布状态")
public interface CourseStatus {
    @ApiModelProperty(value = "未发布")
    String DRAFT="Draft";
    @ApiModelProperty(value = "已发布")
    String NORMAL ="Normal";
}
