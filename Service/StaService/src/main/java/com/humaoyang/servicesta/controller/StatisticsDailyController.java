package com.humaoyang.servicesta.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.servicesta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 统计分析模块
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/staservice/sta")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * 查询某天注册人数
     * @param day 注册日期
     */
    @PostMapping(value = "/registerCount/{day}")
    public R registerCount(@PathVariable(value = "day") String day){
        int count=statisticsDailyService.registerCount(day);
        return R.ok().data("registerCount",count);
    }
    /**
     * 网站统计图表数据
     */
    @GetMapping(value = "/getShowData/{type}/{begin}/{end}")
    public R getShowData(@PathVariable(value = "type")String type,
                         @PathVariable(value = "begin")String begin,
                         @PathVariable(value = "end") String end){
        Map<String, Object> map=statisticsDailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}
