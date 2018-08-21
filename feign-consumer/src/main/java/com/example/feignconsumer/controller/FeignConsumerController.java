package com.example.feignconsumer.controller;

import com.example.feignconsumer.bean.User;
import com.example.feignconsumer.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {
    @Autowired
    HelloService helloService;

    //不带参数消费服务
    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.hello();
    }

    //模拟带参数消费服务
    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer2(){
        StringBuffer sb = new StringBuffer();
        sb.append(helloService.hello("DIDI")).append("\r\n");
        sb.append(helloService.hello("DIDI",19)).append("\r\n");
        sb.append(helloService.hello(new User("IDID",30))).append("\r\n");
        return sb.toString();
    }
}
