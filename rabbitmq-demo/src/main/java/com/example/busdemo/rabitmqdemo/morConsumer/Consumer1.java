/**
 * <p>文件名称: Consumer1.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/1 14:55 </p>
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

package com.example.busdemo.rabitmqdemo.morConsumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * 为了演示一个生产者，多个消费者的情况
 * 第一个消费者
 */
public class Consumer1 {
    /**
     * 队列名
     */
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列，false标识手动返回完成状态，true标识自动,手动提交的话可以能者多劳
        channel.basicConsume(QUEUE_NAME, true, consumer);

        //获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received '" + message + "'");
            //休眠
            Thread.sleep(10);
            //返回确认状态，注释掉表示使用自动确认模式
//            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
