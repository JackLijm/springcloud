package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//动态刷新配置
@RefreshScope
@RestController
public  class ConfigClientController {

    //直接使用value绑定注入
    @Value("${from}")
    private  String from;

    //使用environment获取值
    @Autowired
    private  Environment env;

    @RequestMapping("/from")
    public String propValue(){
        return this.from;
    }

    @RequestMapping("/from2")
    public String propValue2(){
        return this.env.getProperty("from","undefined");
    }
}
