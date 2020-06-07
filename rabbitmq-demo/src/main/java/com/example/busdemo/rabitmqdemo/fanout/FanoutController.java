/**
 * <p>文件名称: TopicController.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2016-</p>

 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>创建日期： 2020/6/7 10:38 </p>
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

import com.example.busdemo.rabitmqdemo.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("fanout")
public class FanoutController {

    @Autowired
    SpringFanoutSender springFanoutSender;

    @RequestMapping("/sendMessage")
    public String sendMessage(){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender("lijm");
        messageDTO.setReceiver("langziming");
        String content = String.format("消息：中央领导即将进行巡视%d", new Random().nextInt());
        messageDTO.setContent(content);
        springFanoutSender.sendMessage(messageDTO);
        return "success";
    }

}
