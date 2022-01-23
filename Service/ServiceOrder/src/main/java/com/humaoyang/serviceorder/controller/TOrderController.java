package com.humaoyang.serviceorder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.humaoyang.commonutils.JwtUtils;
import com.humaoyang.commonutils.R;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.serviceorder.bean.TOrder;
import com.humaoyang.serviceorder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单生成模块
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/edeorder/order")
public class TOrderController {
    @Autowired
    TOrderService tOrderService;

    /**
     * 根据课程id生成订单
     * @param courseId
     * @return
     */
    @PostMapping(value = "/createOrder/{courseId}")
    public R createOrder(@PathVariable(value = "courseId") String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(!StringUtils.hasLength(memberId)){
            throw new CustomizedException(ResultCode.FAILED,"登陆信息有误");
        }
        String orderNo=tOrderService.createOrder(courseId,memberId);
        return R.ok().data("orderId",orderNo);
    }
    @GetMapping(value = "/getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable(value = "orderId") String orderId){
        QueryWrapper<TOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderId);
        TOrder tOrder = tOrderService.getOne(orderQueryWrapper);
        return R.ok().data("item",tOrder);
    }
    //3.根据课程Id和用户ID查询订单表中的订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,
                               @PathVariable String memberId){
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("status",1);
        int count = tOrderService.count(queryWrapper);
        return count > 0;
    }
}
