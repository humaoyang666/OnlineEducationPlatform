package com.humaoyang.eduservice;

import com.alibaba.excel.EasyExcel;
import com.humaoyang.eduservice.bean.Student;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class ExcelTest {
    @Test
    public void write(){
        EasyExcel.write("E://1.xlsx",Student.class).sheet("学生表").doWrite(getList());
    }
    private List<Student> getList(){
        List<Student> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new Student(i,"学生"+i));
        }
        return list;
    }
    @Test
    public void read(){
        EasyExcel.read("E://1.xlsx",Student.class,new ExcelReadListener()).sheet(0).doRead();;
    }
}
