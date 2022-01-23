package com.humaoyang.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.commonutils.R;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.eduservice.bean.EduVideo;
import com.humaoyang.eduservice.client.VodClient;
import com.humaoyang.eduservice.mapper.EduVideoMapper;
import com.humaoyang.eduservice.service.EduVideoService;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author DELL
* @description 针对表【edu_video(课程视频)】的数据库操作Service实现
* @createDate 2022-01-03 17:50:27
*/
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
    implements EduVideoService{
    @Autowired
    private EduVideoMapper eduVideoMapper;
    @Autowired
    VodClient vodClient;
    /**
     *    通过课程id删除小节
      */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoMapper.selectList(eduVideoQueryWrapper);
        List<String> ids = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            String id = eduVideo.getVideoSourceId();
            if(StringUtils.hasLength(id)){
                ids.add(id);
            }
        }
        if(ids.size()>0){
            R r = vodClient.deleteBatch(ids);
            if(!r.getSuccess()){
                throw new CustomizedException(ResultCode.FAILED,r.getMessage());
            }
        }
        eduVideoMapper.delete(eduVideoQueryWrapper);
    }
}




