package com.humaoyang.eduservice.bean.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQuery implements Serializable {
    String title;
    String status;
}
