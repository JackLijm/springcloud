/**
 * <p>文件名称: SpringFanoutConsumer.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/7 11:50 </p>
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
package com.example.busdemo.rabitmqdemo.fanout;

import com.example.busdemo.rabitmqdemo.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpringFanoutGuangxiConsumer {

    @RabbitListener(queues = RabbitMqConstant.FANOUT_GX_QUEUE_NAME)
    public void dealEmail(@Payload String content){
        log.info("广西地区收到中央通知：" + content);
    }
}
