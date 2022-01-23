package com.humaoyang.eduservice.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @ExcelProperty(value = "学生编号",index = 0)
    Integer id;
    @ExcelProperty(value = "学生姓名",index = 1)
    String name;
}
