package com.humaoyang.serviceorder.mapper;

import com.humaoyang.serviceorder.bean.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author DELL
* @description 针对表【t_order(订单)】的数据库操作Mapper
* @createDate 2022-01-15 11:44:14
* @Entity com.humaoyang.serviceorder.bean.TOrder
*/
@Mapper
public interface TOrderMapper extends BaseMapper<TOrder> {

}




