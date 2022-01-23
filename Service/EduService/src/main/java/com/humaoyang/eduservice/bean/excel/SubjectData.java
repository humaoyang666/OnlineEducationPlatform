package com.humaoyang.eduservice.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectData implements Serializable {
    @ExcelProperty(value = "一级分类",index = 0)
    private String oneSubject;
    @ExcelProperty(value = "二级分类",index = 1)
    private String twoSubject;
}
