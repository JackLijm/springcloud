package com.example.configserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//启动config配置
@EnableConfigServer
//服务化
@EnableDiscoveryClient
public class ConfigServerApplication {
	@Value("${user.home}")
	private static String userHome;

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
		System.out.println(userHome);
	}
}
