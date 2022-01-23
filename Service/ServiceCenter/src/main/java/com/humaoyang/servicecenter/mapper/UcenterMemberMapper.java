package com.humaoyang.servicecenter.mapper;

import com.humaoyang.servicecenter.bean.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author DELL
* @description 针对表【ucenter_member(会员表)】的数据库操作Mapper
* @createDate 2022-01-11 11:32:01
* @Entity com.humaoyang.servicecenter.bean.UcenterMember
*/
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int countRegister(@Param(value = "day") String day);
}




