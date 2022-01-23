package com.humaoyang.servicesta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 统计分析模块启动类
 * @author 胡茂洋
 */
@SpringBootApplication
@MapperScan(value = "com.humaoyang.servicesta.mapper")
@ComponentScan(value = "com.humaoyang")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling//开启定时任务
public class StatisticsDailyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsDailyApplication.class,args);
    }
}
