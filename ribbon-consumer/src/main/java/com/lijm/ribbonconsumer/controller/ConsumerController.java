package com.lijm.ribbonconsumer.controller;

import com.lijm.ribbonconsumer.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//服务消费者demo
@RestController
public class ConsumerController {

    @Autowired
    HelloWorldService service;

    @RequestMapping(value = "/ribbon-consumer" ,method = RequestMethod.GET)
    public String helloService(){
        return service.helloWorldService();
    }

    public static void main(String[] args) {
        List<String> students = new ArrayList();
        students.add("123");
        students.add("2");
        students.add("3");
        students.add("4");
        int count = (int) students.stream().filter(s -> s.equals("123")).count();
        System.out.println(count);
    }
}
