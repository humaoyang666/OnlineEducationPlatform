package com.humaoyang.eduservice.client.impl;

import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * vod服务熔断器
 * @author 胡茂洋
 */
@Component
public class VodClientImpl implements VodClient {
    @Override
    public R removeAlyVideo(String id) {
        return R.fail().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.fail().message("删除多个视频出错");
    }
}
