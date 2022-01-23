package com.humaoyang.servicecenter.service;

import com.humaoyang.servicecenter.bean.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.humaoyang.servicecenter.bean.vo.RegisterVo;

/**
* @author DELL
* @description 针对表【ucenter_member(会员表)】的数据库操作Service
* @createDate 2022-01-11 11:32:01
*/
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 用户登陆方法
     * @param ucenterMember 用户登陆信息
     * @return token令牌
     */
    String login(UcenterMember ucenterMember);

    /**
     * 用户注册放法
     * @param registerVo 用户注册信息
     */
    void register(RegisterVo registerVo);

    UcenterMember getMenberByOperid(String openid);

    int countRegister(String day);
}
