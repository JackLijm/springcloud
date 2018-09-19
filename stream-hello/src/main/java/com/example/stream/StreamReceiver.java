package com.example.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * 创建用于接收来自rabbitmq消息的消费者
 */
//EnableBinding指定一个或多个定义了@Input或@Output注解的接口，实现对消息通道（channel）的绑定
@EnableBinding({Sink.class,StreamProducer.class})
public class StreamReceiver {
    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        System.out.println("Receive: " + payload);
    }
}
