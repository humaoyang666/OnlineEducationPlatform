package com.humaoyang.servicesta.client;

import com.humaoyang.commonutils.R;
import com.humaoyang.servicesta.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    @GetMapping(value = "/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable(value = "day")String day);
}
