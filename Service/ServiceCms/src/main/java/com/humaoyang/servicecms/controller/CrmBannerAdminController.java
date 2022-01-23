package com.humaoyang.servicecms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.humaoyang.commonutils.R;
import com.humaoyang.servicecms.bean.CrmBanner;
import com.humaoyang.servicecms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员使用接口
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/educms/banneradmin")
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;
    @ApiOperation(value = "获取banner分页列表")
    @GetMapping(value = "/pageBanner/{page}/{limit}")
    public R pageBanner(
            @ApiParam(name = "page",value = "当前页码",required = true)
            @PathVariable(value = "page") Integer page,
            @ApiParam(name = "limit",value="页面大小",required = true)
            @PathVariable(value = "limit")Integer limit
    ){
        Page<CrmBanner> crmBannerPage = new Page<>(page, limit);
        QueryWrapper<CrmBanner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.orderByAsc("sort");
        crmBannerService.page(crmBannerPage,bannerQueryWrapper);
        List<CrmBanner> items = crmBannerPage.getRecords();
        long total = crmBannerPage.getTotal();
        return R.ok().data("items",items).data("total",total);
    }
    @PostMapping(value = "/addBanner")
    @ApiOperation(value ="添加banner")
    @CacheEvict(value = "banner",allEntries = true)
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }
    @PutMapping(value = "/update")
    @ApiOperation(value = "修改banner")
    @CacheEvict(value = "banner",allEntries = true)
    public R updateById(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }
    @DeleteMapping(value = "/remove/{id}")
    @ApiOperation(value = "删除banner")
    @CacheEvict(value = "banner",allEntries = true)
    public R removeById(@PathVariable(value = "id") String id){
        crmBannerService.removeById(id);
        return R.ok();
    }
}
