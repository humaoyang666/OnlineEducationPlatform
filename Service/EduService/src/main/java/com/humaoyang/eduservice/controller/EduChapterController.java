package com.humaoyang.eduservice.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.EduChapter;
import com.humaoyang.eduservice.bean.chapter.ChapterVo;
import com.humaoyang.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 章节部分Controller
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 根据课程id返回章节列表
     * @param courseId 课程id
     * @return
     */
    @GetMapping(value = "/getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> chapterVoList=eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",chapterVoList);
    }
    //添加章节
    @PostMapping(value = "/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }
    //根据章节id查询
    @GetMapping(value = "/getChapterInfo/{chapterId}")
    public R getChapter(@PathVariable(value = "chapterId") String chapterId){
        EduChapter chapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }
    //修改章节
    @PostMapping(value = "/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }
    //删除章节
    @DeleteMapping(value = "/{chapterId}")
    public R deleteChapter(@PathVariable(value = "chapterId") String chapterId){
        eduChapterService.deleteChapter(chapterId);
        return R.ok();
    }
}
