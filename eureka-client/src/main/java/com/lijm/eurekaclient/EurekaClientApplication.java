package com.lijm.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//erueaka服务客户Dunn
@EnableDiscoveryClient
public class EurekaClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
}
