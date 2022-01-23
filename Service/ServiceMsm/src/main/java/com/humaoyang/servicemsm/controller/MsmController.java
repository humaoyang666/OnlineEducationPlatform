package com.humaoyang.servicemsm.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.servicemsm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 注册短信验证登陆
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/edumsm/msm")
public class MsmController {
    @Autowired
    private  MsmService msmService;

    /**
     * 阿里云发送短信服务
     * todo（账号没钱功能无效）
     * @param phone
     * @return
     */
    @GetMapping(value = "/send/{phone}")
    public R sendSms(@PathVariable(value = "phone") String phone){
        msmService.sendMsm(phone);
        return R.ok();
    }
}
