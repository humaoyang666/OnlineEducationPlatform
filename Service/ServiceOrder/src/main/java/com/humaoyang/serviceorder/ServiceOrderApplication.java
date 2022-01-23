package com.humaoyang.serviceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 订单模块启动类
 * @author 胡茂洋
 */
@SpringBootApplication
@ComponentScan(value = "com.humaoyang")
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class,args);
    }
}
