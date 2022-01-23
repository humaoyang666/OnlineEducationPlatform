package com.humaoyang.serviceorder.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.serviceorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/edeorder/paylog")
public class PayLogController {
    @Autowired
    PayLogService payLogService;
    /**
     * 根据订单号生成二维码
     */
    @GetMapping(value = "/createNative/{orderNo}")
    public R createNative(@PathVariable(value = "orderNo") String orderNo){
        Map<String,Object> map=payLogService.createNative(orderNo);
        return R.ok().data(map);
    }
    //查询订单支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        //返回信息，包含二维码地址，还有其他需要的信息
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if (map == null){
            return R.fail().message("支付出错了");
        }
        //如果不为空，通过map获取订单状态;
        if ("SUCCESS".equals(map.get("trade_state"))){
            //支付成功，更改订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }

        return R.fail().code(25000).message("支付中");
    }
}
