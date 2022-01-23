package com.humaoyang.servicesta.client.impl;

import com.humaoyang.commonutils.R;
import com.humaoyang.servicesta.client.UcenterClient;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public R countRegister(String day) {
        return R.fail().message("服务请求失败");
    }
}
