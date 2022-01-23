package com.humaoyang.servicecenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.commonutils.JwtUtils;
import com.humaoyang.commonutils.MD5;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.servicecenter.bean.UcenterMember;
import com.humaoyang.servicecenter.bean.vo.RegisterVo;
import com.humaoyang.servicecenter.mapper.UcenterMemberMapper;
import com.humaoyang.servicecenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
* @author DELL
* @description 针对表【ucenter_member(会员表)】的数据库操作Service实现
* @createDate 2022-01-11 11:32:01
*/
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember>
    implements UcenterMemberService{
    @Autowired
    UcenterMemberMapper ucenterMemberMapper;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new CustomizedException(ResultCode.FAILED,"请输入账号密码");
        }

        QueryWrapper<UcenterMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("mobile",mobile).eq("is_disabled",0);
        UcenterMember member = this.getOne(memberQueryWrapper);
        if(ObjectUtils.isEmpty(member)){
            throw new CustomizedException(ResultCode.FAILED,"用户不存在,请先注册");
        }
        if(!Objects.equals(MD5.encrypt(password),member.getPassword())){
            throw new CustomizedException(ResultCode.FAILED,"密码错误");
        }
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)||StringUtils.isEmpty(code)){
            throw new CustomizedException(ResultCode.FAILED,"用户输入信息不全");
        }
        String codeSrc = redisTemplate.opsForValue().get(mobile);
        if(StringUtils.isEmpty(codeSrc)||!Objects.equals(code,codeSrc)){
            throw new CustomizedException(ResultCode.FAILED,"验证码错误");
        }
        QueryWrapper<UcenterMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = getOne(memberQueryWrapper);
        if(!ObjectUtils.isEmpty(ucenterMember)){
            throw new CustomizedException(ResultCode.FAILED,"手机号已存在");
        }
        UcenterMember member = new UcenterMember();
        BeanUtils.copyProperties(registerVo,member);
        member.setPassword(MD5.encrypt(member.getPassword()));
        save(member);
    }

    @Override
    public UcenterMember getMenberByOperid(String openid) {
        QueryWrapper<UcenterMember> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("openid",openid).eq("is_disabled",0);
        UcenterMember member = getOne(memberQueryWrapper);
        return member;
    }

    @Override
    public int countRegister(String day) {
        return ucenterMemberMapper.countRegister(day);
    }
}




