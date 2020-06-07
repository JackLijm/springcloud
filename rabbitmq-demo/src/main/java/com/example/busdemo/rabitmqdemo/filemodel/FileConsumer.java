/**
 * <p>文件名称: FileConsumer.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/5 13:11 </p>
 * <p>完成日期：</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 *
 * @version 1.0
 * @author lijm@szkingdom.com
 */

package com.example.busdemo.rabitmqdemo.filemodel;

import com.example.busdemo.rabitmqdemo.morConsumer.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileConsumer extends Thread {
    private Connection connection;
    private String queueName = "queue_node_";
    private String exChangeName = "exchange_node_";
    private String node = "";

    public FileConsumer() {
        super();
    }

    public FileConsumer(Connection connection, String node) {
        super();
        this.connection = connection;
        this.node = node;
    }

    @Override
    public void run() {
        //创建连接
        Channel channel = null;
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String queueNodeName = queueName + node;
        //创建队列
        try {
            channel.queueDeclare(queueNodeName, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            channel.queueBind(queueNodeName, exChangeName + node, exChangeName + node);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        try {
            channel.basicConsume(queueNodeName, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get("D:/test/" + queueNodeName + ".txt");
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!path.getParent().toFile().exists()) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = null;
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String message = new String(delivery.getBody());
            try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("utf-8"), StandardOpenOption.APPEND)) {
                writer.write(message + "\r\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(" [Recv_" + node + "] Received '" + message + "'");
        }
    }
}
