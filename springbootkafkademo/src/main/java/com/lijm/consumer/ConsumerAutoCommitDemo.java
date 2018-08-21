package com.lijm.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

//间隔时间自动提交
public class ConsumerAutoCommitDemo {
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd-HH-mm-ssSSS");
    //消费者配置属性
    public  static Properties consumerProperties(){
        Properties consumerProps = new Properties();
        //设置kafka服务器
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        //设置消费者所在的组
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
        //自动提交设置
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
        //自动提交偏移量频率
        consumerProps.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG," org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG," org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProps;
    }
    public static void main(String[] args) {
        //创建消费者
        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(consumerProperties());
        //订阅两个topics
        consumer.subscribe(Arrays.asList("my-replicated-topic","test"));
        //消费
        while (true) {
            //timeout 每隔多少秒去
            ConsumerRecords<String, String> records = consumer.poll(2000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf(timeFormat.format(new Date()) + " offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
