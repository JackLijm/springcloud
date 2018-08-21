package com.lijm.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloWorldService {
    //ribbon
    @Autowired
    RestTemplate restTemplate;

    //Hystrix熔断器,熔断执行回调方法
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloWorldService(){
        //调用服务已服务名的方式，这个很关键的一个特性
        return restTemplate.getForEntity("http://hello-service/hello",String.class).getBody();
    }

    private String helloFallback(){
        return "error";
    }
}
