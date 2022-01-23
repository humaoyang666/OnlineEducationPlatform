package com.humaoyang.servicesta.service;

import com.humaoyang.servicesta.bean.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author DELL
* @description 针对表【statistics_daily(网站统计日数据)】的数据库操作Service
* @createDate 2022-01-18 11:27:16
*/
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    int registerCount(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
