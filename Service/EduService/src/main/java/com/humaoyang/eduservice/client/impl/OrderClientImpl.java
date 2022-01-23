package com.humaoyang.eduservice.client.impl;

import com.humaoyang.eduservice.client.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
