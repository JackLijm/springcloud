/**
 * <p>文件名称: ConnectionUtil.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2019/7/1 14:59 </p>
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

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ConnectionUtil {

    public static Connection getConnection() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = factory.newConnection();
        return connection;
    }
}

