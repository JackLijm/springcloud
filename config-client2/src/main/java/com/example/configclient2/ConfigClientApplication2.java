package com.example.configclient2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//启动注册中心
@EnableDiscoveryClient
public class ConfigClientApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication2.class, args);
	}
}
