package com.lijm.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

//手动提交偏移量
public class ConsumerManualCommitDemo {
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd:HH:mm:ss:SSSS");
    //消费者配置属性
    public  static Properties consumerProperties(){
        Properties consumerProps = new Properties();
        //设置kafka服务器
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9093");
        //设置消费者所在的组
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
        //自动提交设置
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
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
        final int minBatchSize = 16;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            //客户端轮询取出数据
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                insertIntoDb(buffer);
                //手动提交处理后的偏移量
//                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
       for(ConsumerRecord<String,String> record:buffer){
           System.out.printf(timeFormat.format(new Date()) + " offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
       }
    }
}
