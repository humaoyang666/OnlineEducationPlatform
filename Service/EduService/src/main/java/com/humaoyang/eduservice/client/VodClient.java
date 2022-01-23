package com.humaoyang.eduservice.client;

import com.humaoyang.commonutils.R;
import com.humaoyang.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ServiceVod模块微服务调用
 * @author 胡茂洋
 */
@FeignClient(value = "service-vod",fallback = VodClientImpl.class)
@Component
public interface VodClient {
    /**
     * 远程调用servicevod模块阿里云视频删除方法
     * @param id 阿里云视频id
     */
    @DeleteMapping(value = "/eduvod/video/removeAlyVideo/{id}")
    R removeAlyVideo(@PathVariable(value = "id") String id);

    /**
     * 远程调用servicevod模块批量阿里云视频删除方法
     * @param videoIdList 阿里云视频id集合
     */
    @DeleteMapping(value = "/eduvod/video/delete-batch")
     R deleteBatch(@RequestParam(value = "videoIdList") List<String> videoIdList);
}
