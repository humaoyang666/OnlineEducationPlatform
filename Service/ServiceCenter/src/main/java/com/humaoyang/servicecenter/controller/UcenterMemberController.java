package com.humaoyang.servicecenter.controller;

import com.humaoyang.commonutils.JwtUtils;
import com.humaoyang.commonutils.R;
import com.humaoyang.commonutils.OrderVo.UcenterMemberOrder;
import com.humaoyang.servicecenter.bean.UcenterMember;
import com.humaoyang.servicecenter.bean.vo.RegisterVo;
import com.humaoyang.servicecenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/educenter/member")
public class UcenterMemberController {
    @Autowired
    UcenterMemberService ucenterMemberService;

    /**
     * 用户登陆
     * @param ucenterMember  用户登陆的账号密码
     * @return 带有token令牌的R返回类
     */
    @PostMapping(value = "/login")
    public R login(@RequestBody UcenterMember ucenterMember){
        String token=ucenterMemberService.login(ucenterMember);
        return R.ok().data("token",token);
    }

    /**
     * 用户注册
     * @param registerVo 用户注册信息
     * @return 注册结果
     */
    @PostMapping(value = "/register")
    public R register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    /**
     * 通过token令牌获取用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        return R.ok().data("userInfo",ucenterMember);
    }
    @PostMapping(value = "/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable(value = "id")String id){
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    /**
     * 查询某天注册人数
     * @return 注册人数
     */
    @GetMapping(value = "/countRegister/{day}")
    public R countRegister(@PathVariable(value = "day")String day){
        int count=ucenterMemberService.countRegister(day);
        return R.ok().data("countRegister",count);
    }
}
