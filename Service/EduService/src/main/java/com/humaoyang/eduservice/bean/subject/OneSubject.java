package com.humaoyang.eduservice.bean.subject;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程分类树形结构
 */
@Data
public class OneSubject implements Serializable {
    private String id;
    private String title;
    private List<TwoSubject> children=new ArrayList<>();
}
