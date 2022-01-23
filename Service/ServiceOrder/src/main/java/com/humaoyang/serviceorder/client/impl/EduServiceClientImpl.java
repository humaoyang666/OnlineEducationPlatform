package com.humaoyang.serviceorder.client.impl;

import com.humaoyang.commonutils.OrderVo.EduCourseOrder;
import com.humaoyang.serviceorder.client.EduServiceClient;
import org.springframework.stereotype.Component;

/**
 * eduservice模块远程调用熔断器
 * @author 胡茂洋
 */
@Component
public class EduServiceClientImpl implements EduServiceClient {

    @Override
    public EduCourseOrder getCourseInfoOrder(String id) {
        return null;
    }
}
