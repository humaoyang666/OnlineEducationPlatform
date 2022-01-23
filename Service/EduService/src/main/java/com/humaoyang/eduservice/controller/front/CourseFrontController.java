package com.humaoyang.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.humaoyang.commonutils.JwtUtils;
import com.humaoyang.commonutils.OrderVo.EduCourseOrder;
import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.bean.EduCourse;
import com.humaoyang.eduservice.bean.chapter.ChapterVo;
import com.humaoyang.eduservice.bean.frontvo.CourseQueryVo;
import com.humaoyang.eduservice.bean.frontvo.CourseWebVo;
import com.humaoyang.eduservice.client.OrderClient;
import com.humaoyang.eduservice.service.EduChapterService;
import com.humaoyang.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 前端课程信息
 * @author 胡茂洋
 */
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    /**
     * 课程分页显示
     * @param page
     * @param limit
     * @param courseQueryVo
     * @return
     */
    @Cacheable(value = "coursePage",key = "#page+'/'+#limit+'/'+#courseQueryVo.hashCode()")
    @PostMapping("/getFrontInfo/{page}/{limit}")
    public R getFrontInfo(@PathVariable long page, @PathVariable long limit,
                          @RequestBody(required = false) CourseQueryVo courseQueryVo){

        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageCourse,courseQueryVo);
        return R.ok().data(map);
    }

    /**
     * 通过id获取课程详情信息
     */
    @GetMapping(value = "/getFrontCourseInfo/{id}")
    public R getFrontCourseInfo(@PathVariable(value = "id") String id, HttpServletRequest request){
        CourseWebVo webCourseInfo = eduCourseService.getWebCourseInfo(id);
        List<ChapterVo> chapterVideo= eduChapterService.getChapterVideoByCourseId(id);
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean buy = orderClient.isBuyCourse(id,memberId);
        return R.ok().data("courseWebVo",webCourseInfo).data("chapterVideoList",chapterVideo).data("isBuy",buy);
    }

    /**
     * 课程订单页面
     * @param id
     * @return
     */
    @PostMapping(value = "/getCourseInfoOrder/{id}")
    public EduCourseOrder getCourseInfoOrder(@PathVariable(value = "id") String id){
        CourseWebVo courseWebVo = eduCourseService.getWebCourseInfo(id);
        EduCourseOrder eduCourseOrder = new EduCourseOrder();
        BeanUtils.copyProperties(courseWebVo,eduCourseOrder);
        return eduCourseOrder;
    }
}
