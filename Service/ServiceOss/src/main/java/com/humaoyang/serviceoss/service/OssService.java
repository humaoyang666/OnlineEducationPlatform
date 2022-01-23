package com.humaoyang.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvatar(MultipartFile multipartFile);
}
