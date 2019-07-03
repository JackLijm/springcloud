package com.example.busdemo.rabitmqdemo.normal;

import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
  普通java方式往rabbitmq放消息的生产着
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //create connection to the server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //定义发布队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "Hello world!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("utf-8"));
        System.out.println("[x] sent '" + message + "'");


        channel.close();
        connection.close();

    }

}