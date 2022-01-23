package com.humaoyang.serviceorder.service;

import com.humaoyang.serviceorder.bean.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DELL
* @description 针对表【t_order(订单)】的数据库操作Service
* @createDate 2022-01-15 11:44:14
*/
public interface TOrderService extends IService<TOrder> {
    /**
     * 生成订单
     * @param courseId 课程id
     * @param memberId 用户id
     * @return 订单id
     */
    String createOrder(String courseId, String memberId);
}
