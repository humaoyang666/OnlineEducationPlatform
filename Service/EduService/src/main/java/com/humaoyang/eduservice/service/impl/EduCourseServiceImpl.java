package com.humaoyang.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.eduservice.bean.EduCourse;
import com.humaoyang.eduservice.bean.EduCourseDescription;
import com.humaoyang.eduservice.bean.frontvo.CourseQueryVo;
import com.humaoyang.eduservice.bean.frontvo.CourseWebVo;
import com.humaoyang.eduservice.bean.vo.CourseInfoVo;
import com.humaoyang.eduservice.bean.vo.CoursePublishVo;
import com.humaoyang.eduservice.mapper.EduCourseDescriptionMapper;
import com.humaoyang.eduservice.mapper.EduCourseMapper;
import com.humaoyang.eduservice.service.EduChapterService;
import com.humaoyang.eduservice.service.EduCourseService;
import com.humaoyang.eduservice.service.EduVideoService;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-01-03 17:41:01
*/
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService{
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;
    /**
     * 将课程数据插入到课程表和课程描述表
     * @param courseInfoVo 课程数据实体信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = eduCourseMapper.insert(eduCourse);
        if(insert>0){
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setDescription(courseInfoVo.getDescription());
            eduCourseDescription.setId(eduCourse.getId());
            eduCourseDescriptionMapper.insert(eduCourseDescription);
        }else {
            throw new CustomizedException(ResultCode.FAILED,"课程添加失败");
        }

    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionMapper.selectById(courseId);
        if(eduCourse!=null){
            BeanUtils.copyProperties(eduCourse,courseInfoVo);
        }
        if(eduCourseDescription!=null){
            BeanUtils.copyProperties(eduCourseDescription,courseInfoVo);
        }
        return courseInfoVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int updateCourse = eduCourseMapper.updateById(eduCourse);
        if(updateCourse<=0){
            throw new CustomizedException(ResultCode.FAILED,"课程更新失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        int updateDescription = eduCourseDescriptionMapper.updateById(eduCourseDescription);
        if(updateDescription<=0){
            throw new CustomizedException(ResultCode.FAILED,"课程描述更新失败");
        }
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return eduCourseMapper.getCoursePublishVo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourse(String courseId) {
        eduVideoService.removeByCourseId(courseId);
        eduChapterService.removeByCourseId(courseId);
        eduCourseDescriptionMapper.deleteById(courseId);
        this.removeById(courseId);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseQueryVo courseQueryVo) {
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();
        String buyCountSort = courseQueryVo.getBuyCountSort();
        String gmtCreateSort = courseQueryVo.getGmtCreateSort();
        String priceSort = courseQueryVo.getPriceSort();
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq(StringUtils.hasLength(subjectParentId),"subject_parent_id",subjectParentId)
                .eq(StringUtils.hasLength(subjectId),"subject_id",subjectId)
                .orderByDesc(StringUtils.hasLength(buyCountSort),"buy_count")
                .orderByDesc(StringUtils.hasLength(gmtCreateSort),"gmt_create")
                .orderByDesc(StringUtils.hasLength(priceSort),"price");
        page(pageCourse,courseQueryWrapper);
        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long size = pageCourse.getSize();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();
        long pages = pageCourse.getPages();

        HashMap<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseWebVo getWebCourseInfo(String id) {
        return eduCourseMapper.getWebCourseInfoById(id);
    }
}




