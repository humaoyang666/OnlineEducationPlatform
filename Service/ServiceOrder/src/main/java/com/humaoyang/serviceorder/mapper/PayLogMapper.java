package com.humaoyang.serviceorder.mapper;

import com.humaoyang.serviceorder.bean.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DELL
* @description 针对表【t_pay_log(支付日志表)】的数据库操作Mapper
* @createDate 2022-01-17 11:22:19
* @Entity com.humaoyang.serviceorder.bean.PayLog
*/
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}




