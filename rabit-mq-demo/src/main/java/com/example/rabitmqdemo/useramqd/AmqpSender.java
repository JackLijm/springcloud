package com.example.rabitmqdemo.useramqd;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
/**
 * 通过AMQD封装调用mq的生产者实现
 * ，使用AmqpTemplate来发送消息
 */
public class AmqpSender {

    //使用Amqp封装好的工具来发送
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(){
        String context = "helloamqp" + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("helloamqp",context);
    }
}
