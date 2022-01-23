package com.humaoyang.eduservice.service;

import com.humaoyang.eduservice.bean.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.humaoyang.eduservice.bean.chapter.ChapterVo;

import java.util.List;

/**
* @author 胡茂洋
* @description 针对表【edu_chapter(课程)】的数据库操作Service
* @createDate 2022-01-03 17:47:39
*/
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    void deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}
