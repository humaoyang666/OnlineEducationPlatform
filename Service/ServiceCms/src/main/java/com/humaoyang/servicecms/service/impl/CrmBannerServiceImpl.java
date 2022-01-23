package com.humaoyang.servicecms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humaoyang.servicecms.bean.CrmBanner;
import com.humaoyang.servicecms.service.CrmBannerService;
import com.humaoyang.servicecms.mapper.CrmBannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【crm_banner(首页banner表)】的数据库操作Service实现
* @createDate 2022-01-09 14:41:26
*/
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
    implements CrmBannerService{
    @Autowired
    CrmBannerMapper crmBannerMapper;
}




