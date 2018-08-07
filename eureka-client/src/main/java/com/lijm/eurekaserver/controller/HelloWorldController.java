package com.lijm.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping(name = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        ServiceInstance instance = (ServiceInstance) discoveryClient.getInstances("hello-service").get(0);
        System.out.println("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "hello world";
    }
}
