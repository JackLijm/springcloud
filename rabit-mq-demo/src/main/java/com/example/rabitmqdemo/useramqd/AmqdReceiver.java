package com.example.rabitmqdemo.useramqd;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//消费者监听的消息队列
@RabbitListener(queues = "helloamqp")
/**
 * 通过AMQD封装调用mq的消费者实现
 */
public class AmqdReceiver {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receive : " + hello);
    }
}
