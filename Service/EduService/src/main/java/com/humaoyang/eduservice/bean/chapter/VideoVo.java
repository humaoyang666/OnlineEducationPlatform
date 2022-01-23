package com.humaoyang.eduservice.bean.chapter;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoVo implements Serializable {
    private String id;
    private String title;
    private String videoSourceId;
}
