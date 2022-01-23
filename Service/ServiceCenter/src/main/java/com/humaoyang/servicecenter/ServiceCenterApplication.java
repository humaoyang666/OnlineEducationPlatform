package com.humaoyang.servicecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.humaoyang"})
@EnableDiscoveryClient //服务注册
@EnableFeignClients//服务调用
public class ServiceCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCenterApplication.class,args);
    }
}
