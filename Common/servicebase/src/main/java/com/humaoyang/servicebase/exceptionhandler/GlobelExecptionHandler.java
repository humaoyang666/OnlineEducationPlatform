package com.humaoyang.servicebase.exceptionhandler;

import com.humaoyang.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobelExecptionHandler {
    @ExceptionHandler(value = CustomizedException.class)
    @ResponseBody
    public R error(CustomizedException e){
        return R.fail().code(e.getCode()).message(e.getMsg());
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R error(Exception e){
        return R.fail().message(e.getMessage());
    }

}
