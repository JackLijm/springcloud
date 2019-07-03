/**
 * <p>文件名称: Send.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>
 * <p>公    司: 金证财富南京科技有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/1 15:37 </p>
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

package com.example.busdemo.rabitmqdemo.route;

import com.example.busdemo.rabitmqdemo.morConsumer.ConnectionUtil;
import com.example.rabitmqdemo.morConsumer.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * 路由模式
 */
public class Send {

    /**
     * 交换机名字
     */
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机，设置交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //消息内容
        String messge = "删除商品";
        channel.basicPublish(EXCHANGE_NAME,"delete",null,messge.getBytes());
        System.out.println("[x] Sent '" + messge + "'");
        messge = "增加商品";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,messge.getBytes());
        System.out.println("[x] Sent '" + messge + "'");
        channel.close();
        connection.close();
    }
}
