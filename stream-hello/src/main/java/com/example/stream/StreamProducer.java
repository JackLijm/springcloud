package com.example.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * 创建一个将Input消息通道作为输出通道的接口
 */
public interface StreamProducer {

    @Output(Sink.INPUT)
    MessageChannel output();
}
