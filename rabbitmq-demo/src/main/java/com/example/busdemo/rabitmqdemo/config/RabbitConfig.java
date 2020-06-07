package com.example.busdemo.rabitmqdemo.config;



import com.example.busdemo.rabitmqdemo.constant.RabbitMqConstant;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * 1.创建一个队列，用于测试exchange模式为direct的
     * direct模式：Routing key = message才会进入该队列
     * @return
     */
    @Bean
    public Queue directMessageQueue(){
        return new Queue(RabbitMqConstant.DIRECT_MESSAGE_QUEUE_NAME);
    }

    /**
     * 2.创建一个队列，用于测试exchange模式为direct的
     * direct模式：Routing key = email才会进入该队列
     * @return
     */
    @Bean
    public Queue directEmailQueue(){
        return new Queue(RabbitMqConstant.DIRECT_EMAIL_QUEUE_NAME);
    }

    /**
     * 3.定义一个direct类型的交互器
     * @return
     */
        @Bean
        DirectExchange directExchange(){
            return new DirectExchange(RabbitMqConstant.DIRECT_EXCHANGE_NAME);
        }

    /**
     * 4.exchange与queue绑定
     */
    @Bean
    Binding bindingDirectExchangeMessage(@Qualifier("directMessageQueue") Queue directMessageQueue, DirectExchange directExchange){
       return BindingBuilder.bind(directMessageQueue).to(directExchange).with(RabbitMqConstant.DIRECT_MESSAGE_ROUTING_KEY_NAME);
    }
    /**
     * 5.exchange与queue绑定
     */
    @Bean
    Binding bindingDirectExchangeEmail(@Qualifier("directEmailQueue") Queue directEmailQueue, DirectExchange directExchange){
        return BindingBuilder.bind(directEmailQueue).to(directExchange).with(RabbitMqConstant.DIRECT_EMAIL_ROUTING_KEY_NAME);
    }

    /**
     * 创建一个队列，用于测试exchange模式为topic的
     * @return
     */
    @Bean
    public Queue topicEmailQueue(){
        return new Queue(RabbitMqConstant.TOPIC_EMAIL_QUEUE_NAME);
    }

    /**
     * 创建一个队列，用于测试exchange模式为topic的
     * 根据key进行模糊匹配后投递，一个消息
     * @return
     */
    @Bean
    public Queue topicMessageQueue(){
        return new Queue(RabbitMqConstant.TOPIC_MESSAGE_QUEUE_NAME);
    }
    
    /**
     * 定义一个topic类型的交互器
     * @return
     */
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(RabbitMqConstant.TOPIC_EXCHANGE_NAME);
    }


    /**
     * topic-exchange与queue绑定
     */
    @Bean
    Binding bindingTopicExchangeMessage(@Qualifier("topicMessageQueue") Queue topicMessageQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(topicMessageQueue).to(topicExchange).with(RabbitMqConstant.TOPIC_MESSAGE_ROUTING_KEY_NAME+".*");
    }
    /**
     * topic-exchange与queue绑定
     */
    @Bean
    Binding bindingTopicExchangeEmail(@Qualifier("topicEmailQueue") Queue topicEmailQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(topicEmailQueue).to(topicExchange).with(RabbitMqConstant.TOPIC_EMAIL_ROUTING_KEY_NAME+".*");
    }
    
    /**
     * 创建一个队列，用于测试exchange模式为fanout的
     * fanout模式：一个消息进来，投递给所有与exchange绑定的消息队列
     * @return
     */
    @Bean
    public Queue fanoutEmailQueue(){
        return new Queue(RabbitMqConstant.FANOUT_GX_QUEUE_NAME);
    }

    /**
     * 创建一个队列，用于测试exchange模式为fanout的
     * 根据key进行模糊匹配后投递，一个消息
     * @return
     */
    @Bean
    public Queue fanoutMessageQueue(){
        return new Queue(RabbitMqConstant.FANOUT_GD_QUEUE_NAME);
    }
    
    /**
     * 定义一个fanout类型的交互器
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitMqConstant.FANOUT_EXCHANGE_NAME);
    }

    /**
     * Fanout-exchange与queue绑定
     */
    @Bean
    Binding bindingFanoutExchangeMessage(@Qualifier("fanoutMessageQueue") Queue fanoutMessageQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutMessageQueue).to(fanoutExchange);
    }
    /**
     * Fanout-exchange与queue绑定
     */
    @Bean
    Binding bindingFanoutExchangeEmail(@Qualifier("fanoutEmailQueue") Queue fanoutEmailQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }
}
