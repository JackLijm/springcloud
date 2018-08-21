package com.lijm.ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Observable;

@SpringBootApplication
//注册成Eureka客户端，以获得服务发现的能力
@EnableDiscoveryClient
//开启断路器
@EnableHystrix
public class RibbonConsumerApplication {

	@Bean
	//开启ribbon负载均衡
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();

	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}
}
