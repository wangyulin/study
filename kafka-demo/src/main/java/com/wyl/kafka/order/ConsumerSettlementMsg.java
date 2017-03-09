package com.wyl.kafka.order;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class ConsumerSettlementMsg {

    public static void main(String[] args) {
        String groupId = "group01";
        Properties props = new Properties();
        props.put("group.id", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // serializer.class为消息的序列化类
        props.put("value.deserializer", "com.wyl.kafka.order.SettlementMsgDeserializer");
        // 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
        props.put("bootstrap.servers", "wangyulin-test-host:9092");
        // 设置Partition类, 对队列进行合理的划分
        //props.put("partitioner.class", "idoall.testkafka.Partitionertest");

        KafkaConsumer<String, SettlementMsg> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test002"));

        while (true) {
            ConsumerRecords<String, SettlementMsg> records = consumer.poll(100);
            for (final ConsumerRecord<String, SettlementMsg> record : records) {
                System.out.println("Receive: " + record.value().getType());
            }
        }
    }

}
