/**
 * <p>文件名称: SpringEmailConsumer.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/7 10:08 </p>
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j

public class SpringTopicMessageConsumer {

    @Transactional(rollbackFor = Exception.class)
    @RabbitListener(queues = RabbitMqConstant.TOPIC_MESSAGE_QUEUE_NAME)
    @RabbitHandler
    public void sendMessage(@Payload String content) {
        MessageDTO messageDTO = new Gson().fromJson(content,MessageDTO.class);
        log.info("收到消息" + messageDTO);
        //剩下处理逻辑
    }

}
