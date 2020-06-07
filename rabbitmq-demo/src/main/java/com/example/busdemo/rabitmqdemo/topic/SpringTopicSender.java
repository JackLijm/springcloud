/**
 * <p>文件名称: SpringTopicSender.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/7 10:25 </p>
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

import com.example.busdemo.rabitmqdemo.constant.RabbitMqConstant;
import com.example.busdemo.rabitmqdemo.dto.EmailDTO;
import com.example.busdemo.rabitmqdemo.dto.MessageDTO;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringTopicSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息内容
     *
     * @param messageDTO
     */
    public void sendMessage(MessageDTO messageDTO) {
        String content = new Gson().toJson(messageDTO);
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_EXCHANGE_NAME, RabbitMqConstant.TOPIC_MESSAGE_ROUTING_KEY_NAME+".456", content);
    }

    /**
     * 发送消息内容
     *
     * @param emailDTO
     */
    public void sendEmail(EmailDTO emailDTO) {
        String content = new Gson().toJson(emailDTO);
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_EXCHANGE_NAME, RabbitMqConstant.TOPIC_EMAIL_ROUTING_KEY_NAME+".123", content);
    }
}
