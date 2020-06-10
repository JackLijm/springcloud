package com.lijm.consulclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulClientApplication {
    public static final Logger logger = LoggerFactory.getLogger(ConsulClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsulClientApplication.class, args);
    }
}
