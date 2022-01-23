package com.humaoyang.eduservice.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@ApiModel(value = "教师查询条件类")
@Data
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "讲师姓名")
    private String name;
    @ApiModelProperty(value = "讲师级别")
    private Integer level;
    @ApiModelProperty(value = "开始时间")
    private String begin;
    @ApiModelProperty(value = "结束时间")
    private String end;
}
