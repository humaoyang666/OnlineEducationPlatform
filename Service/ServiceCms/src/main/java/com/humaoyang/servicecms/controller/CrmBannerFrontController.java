package com.humaoyang.servicecms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.humaoyang.commonutils.R;
import com.humaoyang.servicecms.bean.CrmBanner;
import com.humaoyang.servicecms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前端用户使用借口
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/educms/bannerfront")
public class CrmBannerFrontController {
    @Autowired
    CrmBannerService crmBannerService;
    @GetMapping(value = "/getAllBanner")
    @Cacheable(key = "'getAllBanner'",value = "banner")
    public R getAllBanner(){
        QueryWrapper<CrmBanner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.orderByAsc("sort").last("limit 2");
        List<CrmBanner> list = crmBannerService.list(bannerQueryWrapper);
        return R.ok().data("list",list);
    }
}
