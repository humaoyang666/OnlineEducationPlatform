package com.humaoyang.serviceorder.client.impl;

import com.humaoyang.commonutils.OrderVo.UcenterMemberOrder;
import com.humaoyang.serviceorder.client.ServiceCenterClient;
import org.springframework.stereotype.Component;

@Component
public class ServiceCenterClientImpl implements ServiceCenterClient {
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        return null;
    }
}
