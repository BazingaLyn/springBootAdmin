package com.geekcattle.controller.swagger;

import com.geekcattle.task.AccountAjfTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author liguolin
 * @create 2017-12-26 14:58
 **/
@RestController
public class LynccController {

    @Resource
    private AccountAjfTask accountAjfTask;


    @RequestMapping(value = "/index1",method = {RequestMethod.GET})
    public String index(){
        accountAjfTask.compute();
        return "success";
    }

}
