package com.humaoyang.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.eduservice.bean.EduChapter;
import com.humaoyang.eduservice.bean.EduVideo;
import com.humaoyang.eduservice.bean.chapter.ChapterVo;
import com.humaoyang.eduservice.bean.chapter.VideoVo;
import com.humaoyang.eduservice.mapper.EduChapterMapper;
import com.humaoyang.eduservice.mapper.EduVideoMapper;
import com.humaoyang.eduservice.service.EduChapterService;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* @author DELL
* @description 针对表【edu_chapter(课程)】的数据库操作Service实现
* @createDate 2022-01-03 17:47:39
*/
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
    implements EduChapterService{
    @Autowired
    private EduChapterMapper eduChapterMapper;
    @Autowired
    private EduVideoMapper eduVideoMapper;
    //根据课程id返回课程大纲列表
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //查询出所有课程的章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId).orderByAsc("sort");
        List<EduChapter> eduChapterList = eduChapterMapper.selectList(chapterQueryWrapper);
        ArrayList<ChapterVo> res = new ArrayList<>();
        //查询出所有本课程的小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId).orderByAsc("sort");
        List<EduVideo> eduVideoList = eduVideoMapper.selectList(videoQueryWrapper);
        for (EduChapter chapter : eduChapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            res.add(chapterVo);
            for (EduVideo eduVideo : eduVideoList) {
                if(Objects.equals(eduVideo.getChapterId(),chapterVo.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    chapterVo.getChildren().add(videoVo);
                }
            }
        }
        return res;
    }

    /**
     * 通过章节id删除章节
     * 如果章节存在小节禁止删除
     * @param chapterId
     */
    @Override
    public void deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("chapter_id",chapterId);
        Integer count = eduVideoMapper.selectCount(videoQueryWrapper);
        if(count!=0){
            throw new CustomizedException(ResultCode.FAILED,"请先删除小节");
        }
        eduChapterMapper.deleteById(chapterId);
    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        eduChapterMapper.delete(chapterQueryWrapper);
    }
}




