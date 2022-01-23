package com.humaoyang.serviceorder.service;

import com.humaoyang.serviceorder.bean.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author DELL
* @description 针对表【t_pay_log(支付日志表)】的数据库操作Service
* @createDate 2022-01-17 11:22:19
*/
public interface PayLogService extends IService<PayLog> {

    //生成微信支付二维码
    Map createNative(String orderNo);

    //查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //更改订单状态
    void updateOrderStatus(Map<String, String> map);
}
