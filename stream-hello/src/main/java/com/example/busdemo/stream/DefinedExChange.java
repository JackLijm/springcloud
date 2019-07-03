package com.example.busdemo.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 创建一个将Input消息通道作为输出通道的接口
 */
public interface DefinedExChange {
    String INPUT = "lijmchannel";
    String OUTPUT = "lijmchannel";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}
