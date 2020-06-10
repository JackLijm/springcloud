/**
 * <p>文件名称: SayHelloController.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/6 10:57 </p>
 * <p>完成日期：</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 *
 * @version 1.0
 * @author lijm@szkingdom.com
 */
package com.lijm.consulclient.controller;

import com.lijm.consulclient.outinteface.Client1Interface;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("client2")
public class SayHelloController {
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Client1Interface client1Interface;

    @RequestMapping("/sayHello")
    public String sayHelloClient1(){
        return "hello world client2";
    }

    @RequestMapping("/getClient1Info")
    public String getClient1Info(){
        return  discoveryClient.getInstances("consul-client1").toString();
    }

    /**
     * 微服务之间通讯方式1：
     * 使用springboot自带的RestTemplate进行请求
     * @return
     */
    @RequestMapping("/communication1")
    public String communication1(){
        String forObject = restTemplate.postForObject("http://consul-client1/client1/sayHello", new String("Jone"), String.class);
        return  forObject;
    }

    /**
     * 微服务之间通讯方式2：
     * 使用Feign进行请求
     * @return
     */
    @RequestMapping("/communicationByFeign")
    public String communicationByFeign(){
        String forObject = client1Interface.sayHelloClient1("李佳明");
        return  forObject;
    }

    /**
     * 使用Hystrix熔断
     * @return
     */
    @RequestMapping("/testHystrix")
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String testHystrix(){
        String forObject = client1Interface.sayHelloClient1("李佳明");
        return  forObject;
    }

    public String helloFallback(){
        return "服务调用失败了";
    }
}
