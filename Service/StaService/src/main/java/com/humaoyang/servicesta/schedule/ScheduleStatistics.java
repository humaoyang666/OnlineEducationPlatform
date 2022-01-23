package com.humaoyang.servicesta.schedule;

import com.humaoyang.commonutils.DateUtil;
import com.humaoyang.servicesta.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时统计网站数据
 * @author 胡茂洋
 */
@Component
public class ScheduleStatistics {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    @Scheduled(cron = "0 0 1 1/1 * ? ")
    public void scheduleRegisterCount(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }

}
