package com.humaoyang.servicesta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.commonutils.R;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.servicesta.bean.StatisticsDaily;
import com.humaoyang.servicesta.client.UcenterClient;
import com.humaoyang.servicesta.service.StatisticsDailyService;
import com.humaoyang.servicesta.mapper.StatisticsDailyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author DELL
* @description 针对表【statistics_daily(网站统计日数据)】的数据库操作Service实现
* @createDate 2022-01-18 11:27:16
*/
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily>
    implements StatisticsDailyService{
    @Autowired
    StatisticsDailyMapper statisticsDailyMapper;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public int registerCount(String day) {
        R r = ucenterClient.countRegister(day);
        if(!r.getSuccess()){
            throw new CustomizedException(ResultCode.FAILED,"请求处理失败");
        }
        Integer countRegister = (Integer) r.getData().get("countRegister");
        QueryWrapper<StatisticsDaily> statisticsDailyQueryWrapper = new QueryWrapper<>();
        statisticsDailyQueryWrapper.eq("date_calculated",day);
        StatisticsDaily one = getOne(statisticsDailyQueryWrapper);

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        if(one!=null){
            statisticsDaily.setId(one.getId());
        }
        Random random = new Random();
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setRegisterNum(countRegister);
        statisticsDaily.setLoginNum(random.nextInt(100));
        statisticsDaily.setCourseNum(random.nextInt(100));
        statisticsDaily.setVideoViewNum(random.nextInt(100));
        this.saveOrUpdate(statisticsDaily);
        return countRegister;
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("date_calculated",type).between("date_calculated",begin,end);
        List<StatisticsDaily> statisticsDailyList = statisticsDailyMapper.selectList(queryWrapper);
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> countList = new ArrayList<>();
        for (StatisticsDaily statisticsDaily : statisticsDailyList) {
            dateList.add(statisticsDaily.getDateCalculated());
            if("register_num".equals(type)){
                countList.add(statisticsDaily.getRegisterNum());
            }else if("login_num".equals(type)){
                countList.add(statisticsDaily.getLoginNum());
            }else if("video_view_num".equals(type)){
                countList.add(statisticsDaily.getVideoViewNum());
            }else if("course_num".equals(type)){
                countList.add(statisticsDaily.getCourseNum());
            }
        }
        Map<String, Object> res=new HashMap<>();
        res.put("dateList",dateList);
        res.put("typeList",countList);
        return res;
    }
}




