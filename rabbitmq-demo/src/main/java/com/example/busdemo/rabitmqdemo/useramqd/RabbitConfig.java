package com.example.busdemo.rabitmqdemo.useramqd;



import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    //创建队列
    @Bean
    public Queue helloQueue(){
        return new Queue("helloamqp");
    }
}
