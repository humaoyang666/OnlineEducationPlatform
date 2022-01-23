package com.humaoyang.serviceoss.controller;

import com.humaoyang.commonutils.R;
import com.humaoyang.serviceoss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * oss图片上传controller
 * @author 胡茂洋
 */
@RestController
@RequestMapping(value = "/eduoss")
public class OssController {
    @Autowired
    OssService ossService;
    @PostMapping(value = "/fileoss")
    public R uploadOssFile(MultipartFile multipartFile){
        String url=ossService.uploadFileAvatar(multipartFile);
        return R.ok().data("url",url);
    }
}
