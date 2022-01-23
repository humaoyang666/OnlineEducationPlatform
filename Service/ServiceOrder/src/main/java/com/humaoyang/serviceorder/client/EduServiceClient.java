package com.humaoyang.serviceorder.client;

import com.humaoyang.commonutils.OrderVo.EduCourseOrder;
import com.humaoyang.serviceorder.client.impl.EduServiceClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * eduservice模块远程调用
 * @author 胡茂洋
 */
@Component
@FeignClient(value = "service-edu",fallback = EduServiceClientImpl.class)
public interface EduServiceClient {
    @PostMapping(value = "/eduservice/coursefront/getCourseInfoOrder/{id}")
    public EduCourseOrder getCourseInfoOrder(@PathVariable(value = "id") String id);
}
