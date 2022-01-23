package com.humaoyang.serviceoss;

import com.humaoyang.serviceoss.utils.ConstantPropertiesUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceOssTest {
    @Autowired
    ConstantPropertiesUtils constantPropertiesUtils;
    @Test
    public void testOss(){
        List<String> videoIdList=new ArrayList<>();
        videoIdList.add("aa");
        videoIdList.add("");
        videoIdList.add("cc");
        videoIdList.add(null);
        videoIdList.add("Dd");

        System.out.println(videoIdList);
    }
}
