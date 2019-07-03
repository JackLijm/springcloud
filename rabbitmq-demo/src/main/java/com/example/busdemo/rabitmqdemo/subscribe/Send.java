/**
 * <p>文件名称: Send.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>
 * <p>公    司: 金证财富南京科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/1 15:24 </p>
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

package com.example.busdemo.rabitmqdemo.subscribe;

import com.example.busdemo.rabitmqdemo.morConsumer.ConnectionUtil;
import com.example.rabitmqdemo.morConsumer.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 订阅模式， 生产者-》交换机-》队列—>消费者
 */
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
