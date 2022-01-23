package com.humaoyang.servicecenter.controller;

import com.google.gson.Gson;
import com.humaoyang.commonutils.JwtUtils;
import com.humaoyang.commonutils.ResultCode;
import com.humaoyang.servicebase.exceptionhandler.CustomizedException;
import com.humaoyang.servicecenter.bean.UcenterMember;
import com.humaoyang.servicecenter.service.UcenterMemberService;
import com.humaoyang.servicecenter.utils.ConstantAttr;
import com.humaoyang.servicecenter.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * 微信登陆
 * @author 胡茂洋
 */
@Controller
@RequestMapping(value = "/api/ucenter/wx")
public class WechatApiController {
    @Autowired
    private UcenterMemberService ucenterMemberService;
    @GetMapping(value = "/callback")
    public String callback(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state, HttpSession session){
        try {
            //2、拿着code请求 微信的固定地址，得到两个值access_token和openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    ConstantAttr.APPID,
                    ConstantAttr.APPSECRET,
                    code);
            //请求这个拼接好的地址，得到返回两个值access_token和openid
            //使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            //使用gson转换工具Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);

            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");


            //判断该微信信息是否注册过
            UcenterMember members = ucenterMemberService.getMenberByOperid(openid);
            if (members == null){
                //3\拿着access_token和openid，再去请求微信提供的固定地址，获取扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";

                //再次拼接微信地址
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);

                String userInfo = HttpClientUtils.get(userInfoUrl);

                //获取的微信个人信息json信息进行转换
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");//昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");//头像
                //把微信信息注册到数据库中
                members = new UcenterMember();
                members.setNickname(nickname);
                members.setOpenid(openid);
                members.setAvatar(headimgurl);
//                members.setSex(sex);
                ucenterMemberService.save(members);
            }

            //使用jwt生成token字符串
            String jwtToken = JwtUtils.getJwtToken(members.getId(), members.getNickname());
            //返回首页面
            return "redirect:http://localhost:3000?token="+jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizedException(ResultCode.FAILED,"登录失败");
        }
    }
    @GetMapping(value = "/login")
    public String getWxCode(){
        // 微信开放平台授权baseUrl
        //? %s相当于占位符，可以填充参数
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        //授权码需要传入加密过的URL,必须使用
        String redirectUrl = ConstantAttr.REDIRECTURL;//获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");//url编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //设置%s参数值
        String url = String.format(
                baseUrl,
                ConstantAttr.APPID,
                redirectUrl,
                "xueba");
        //请求微信地址,重定向的方式
        return "redirect:" + url;
    }
}
