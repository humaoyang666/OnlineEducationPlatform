package com.humaoyang.serviceorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.commonutils.OrderVo.EduCourseOrder;
import com.humaoyang.commonutils.OrderVo.UcenterMemberOrder;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.serviceorder.bean.TOrder;
import com.humaoyang.serviceorder.client.EduServiceClient;
import com.humaoyang.serviceorder.client.ServiceCenterClient;
import com.humaoyang.serviceorder.mapper.TOrderMapper;
import com.humaoyang.serviceorder.service.TOrderService;
import com.humaoyang.serviceorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
* @author DELL
* @description 针对表【t_order(订单)】的数据库操作Service实现
* @createDate 2022-01-15 11:44:14
*/
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder>
    implements TOrderService{
    @Autowired
    private EduServiceClient eduServiceClient;
    @Autowired
    private ServiceCenterClient serviceCenterClient;
    @Override
    public String createOrder(String courseId, String memberId) {

        EduCourseOrder courseInfoOrder = eduServiceClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder userInfoOrder = serviceCenterClient.getUserInfoOrder(memberId);
        if(ObjectUtils.isEmpty(courseInfoOrder)||ObjectUtils.isEmpty(userInfoOrder)){
            throw new CustomizedException(ResultCode.FAILED,"订单生产失败");
        }
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfoOrder.getTitle());
        tOrder.setCourseCover(courseInfoOrder.getCover());
        tOrder.setTeacherName(courseInfoOrder.getTeacherName());
        tOrder.setTotalFee(courseInfoOrder.getPrice());
        tOrder.setMemberId(memberId);
        tOrder.setMobile(userInfoOrder.getMobile());
        tOrder.setNickname(userInfoOrder.getNickname());
        tOrder.setStatus(0);
        tOrder.setPayType(1);
        save(tOrder);
        return tOrder.getOrderNo();
    }
}




