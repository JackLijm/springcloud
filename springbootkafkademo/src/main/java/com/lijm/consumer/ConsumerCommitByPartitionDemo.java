package com.lijm.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

//根据每一个partition的消费情况提交偏移量
public class ConsumerCommitByPartitionDemo {
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMdd:HH:mm:ss:SSSS");

    //消费者配置属性
    public static Properties consumerProperties() {
        Properties consumerProps = new Properties();
        //设置kafka服务器
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        //设置消费者所在的组
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        //自动提交设置
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, " org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, " org.apache.kafka.common.serialization.StringDeserializer");
        return consumerProps;
    }

    public static void main(String[] args) {
        //创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(consumerProperties());
        //订阅两个topics
        consumer.subscribe(Arrays.asList("my-replicated-topic", "test"));
        //消费
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        try {
            while (true) {
                //客户端轮询取出数据
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (TopicPartition partition : records.partitions()) {
                    //获取某个分区下的事件
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        System.out.println(record.offset() + ": " + record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    //因为下次获取是从没处理的位置开始，因此需要在已处理的位置+1，移动到下一个没处理的位置
//                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } finally {
            consumer.close();
        }
    }

    private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
        for (ConsumerRecord<String, String> record : buffer) {
            System.out.printf(timeFormat.format(new Date()) + " offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        }
    }
}
