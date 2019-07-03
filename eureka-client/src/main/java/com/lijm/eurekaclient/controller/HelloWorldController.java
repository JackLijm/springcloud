package com.lijm.eurekaclient.controller;

import com.lijm.eurekaclient.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.apache.log4j.Logger;


@RestController
public class HelloWorldController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;
    @RequestMapping(name = "/hello",method = RequestMethod.GET)
    public String sayHello(HttpServletRequest request){
        logger.info("===<call trace-2,TraceId={"+request.getHeader("X-B3-TraceId")+"},SpanId={"+request.getHeader("X-B3-SpanId")+"}>===");
        List<ServiceInstance> instances =  discoveryClient.getInstances("hello-service");
        for(ServiceInstance instance:instances) {
            logger.info("hello-service服务列表, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", port:" + instance.getPort());
        }
        return "hello world";
    }


    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "Hello " + name;
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age){
        return new User(name,age);
    }

    @RequestMapping(value = "/hello3",method = RequestMethod.POST)
    public String hello(@RequestBody User user){
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
