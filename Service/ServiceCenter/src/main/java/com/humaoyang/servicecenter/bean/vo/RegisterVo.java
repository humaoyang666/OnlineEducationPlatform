package com.humaoyang.servicecenter.bean.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册vo
 * @author 胡茂洋
 */
@Data
public class RegisterVo implements Serializable {
    private String mobile;
    private String password;
    private String nickname;
    private String code;
}
