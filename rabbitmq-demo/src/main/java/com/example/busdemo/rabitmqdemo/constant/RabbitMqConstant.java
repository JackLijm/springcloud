/**
 * <p>文件名称: RabbitMQConstant.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/7 10:10 </p>
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
package com.example.busdemo.rabitmqdemo.constant;

public class RabbitMqConstant {
    public static final String TOPIC_MESSAGE_QUEUE_NAME = "topic-mode-message-queue";
    public static final String TOPIC_EMAIL_QUEUE_NAME = "topic-mode-email-queue";
    public static final String DIRECT_MESSAGE_QUEUE_NAME = "direct-mode-message-queue";
    public static final String DIRECT_EMAIL_QUEUE_NAME = "direct-mode-email-queue";
    public static final String FANOUT_GD_QUEUE_NAME = "fanout-mode-gd-queue";
    public static final String FANOUT_GX_QUEUE_NAME = "fanout-mode-gx-queue";

    public static final String TOPIC_EXCHANGE_NAME = "topic-exchange";
    public static final String DIRECT_EXCHANGE_NAME = "direct-exchange";
    public static final String FANOUT_EXCHANGE_NAME = "fanout-exchange";

    public static final String TOPIC_MESSAGE_ROUTING_KEY_NAME = "topic.message";
    public static final String TOPIC_EMAIL_ROUTING_KEY_NAME = "topic.email";
    public static final String DIRECT_MESSAGE_ROUTING_KEY_NAME = "direct.message";
    public static final String DIRECT_EMAIL_ROUTING_KEY_NAME = "direct.email";
    public static final String FANOUT_MESSAGE_ROUTING_KEY_NAME = "";
    public static final String FANOUT_EMAIL_ROUTING_KEY_NAME = "";

    
}
