package com.humaoyang.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class R {
    private Integer code;
    private String message;
    private Boolean success;
    private Map<String,Object> data=new HashMap<>();

    private R() {
    }
    public static R ok(){
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        r.setSuccess(true);
        return r;
    }
    public static R fail(){
        R r = new R();
        r.setCode(ResultCode.FAILED);
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R data(String key,Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String,Object> data){
        this.data=data;
        return this;
    }
}
