package com.humaoyang.eduservice.controller;

import com.humaoyang.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/eduservice/user")
public class EduLoginController {
    @PostMapping(value = "/login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping(value = "/info")
    public R info(){
        return R.ok().data("roles","admin").data("name","admin")
                .data("avatar","https://i0.hdslb.com/bfs/face/38909155bb33da8d8ffc16c59a2aad3f3a0ddfca.jpg@160w_160h_1c_1s.webp");
    }
}
