package com.humaoyang.eduservice.bean.chapter;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo implements Serializable {
    private String id;
    private String title;
    private List<VideoVo> children=new ArrayList<>();
}
