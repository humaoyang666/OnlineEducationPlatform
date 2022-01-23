package com.humaoyang.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.humaoyang.eduservice.bean.EduVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author DELL
* @description 针对表【edu_video(课程视频)】的数据库操作Mapper
* @createDate 2022-01-03 17:50:27
* @Entity com.humaoyang.eduservice.bean.EduVideo
*/
@Mapper
public interface EduVideoMapper extends BaseMapper<EduVideo> {

}




