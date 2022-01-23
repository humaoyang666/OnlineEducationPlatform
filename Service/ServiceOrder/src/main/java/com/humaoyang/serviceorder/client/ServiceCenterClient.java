package com.humaoyang.serviceorder.client;

import com.humaoyang.commonutils.OrderVo.UcenterMemberOrder;
import com.humaoyang.serviceorder.client.impl.ServiceCenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "service-ucenter",fallback = ServiceCenterClientImpl.class)
public interface ServiceCenterClient {
    @PostMapping(value = "/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable(value = "id")String id);
}
