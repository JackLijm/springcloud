package com.lijm.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
//kafka生产者代码实现demo
public class ProductorDemo {

    public static void main(String[] args) {
        //生产者属性
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        //控制请求的完整性， all-当所有记录都提交才会结束阻塞
        producerProps.put(ProducerConfig.ACKS_CONFIG,"all");
        producerProps.put(ProducerConfig.RETRIES_CONFIG,0);
        producerProps.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        producerProps.put(ProducerConfig.LINGER_MS_CONFIG,1);
        producerProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        Producer<String ,String > producer = new KafkaProducer<String, String>(producerProps);
        for(int i=0;i<100;i++){
            //异步的过程，往队列里方东西之后就马上返回了
            producer.send(new ProducerRecord<String,String>("my-replicated-topic",Integer.toString(i),Integer.toString(i)));
        }

        producer.close();
    }
}
