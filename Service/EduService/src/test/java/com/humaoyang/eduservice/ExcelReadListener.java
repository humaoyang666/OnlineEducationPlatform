package com.humaoyang.eduservice;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.humaoyang.eduservice.bean.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelReadListener extends AnalysisEventListener<Student> {
    List<Student> list = new ArrayList<>();

    @Override
    public void invoke(Student data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        list.forEach(System.out::println);
    }
}
