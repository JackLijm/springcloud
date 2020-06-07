/**
 * <p>文件名称: Sender.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/1 16:27 </p>
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

package com.example.busdemo.rabitmqdemo.topic;

import com.example.busdemo.rabitmqdemo.morConsumer.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 主题模式，也可以叫做通配符模式
 */
public class Sender {
    private final static String EX_CHANGE_TOPIC = "test_exchange_topic";

    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EX_CHANGE_TOPIC,"topic");

        //消息内容
        String message = "Hello World";
        channel.basicPublish(EX_CHANGE_TOPIC,"routekey.1",null,message.getBytes());
        System.out.println("[x] send '" + message + "'");

        channel.close();
        connection.close();

    }
}
