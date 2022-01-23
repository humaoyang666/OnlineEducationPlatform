package com.humaoyang.eduservice.client;

import com.humaoyang.eduservice.client.impl.OrderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 胡茂阳
 */
@Component
@FeignClient(value = "service-order",fallback = OrderClientImpl.class)
public interface OrderClient {
    //3.根据课程Id和用户ID查询订单表中的订单状态
    @GetMapping("/edeorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable(value = "courseId") String courseId,
                               @PathVariable(value = "memberId") String memberId);
}
