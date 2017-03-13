package com.wyl.kafka.official;

import com.wyl.kafka.order.SettlementMsg;
import kafka.utils.ShutdownableThread;
import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;
/**
 * Created by wangyulin on 13/03/2017.
 */

public class Consumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, byte[]> consumer;
    private final String topic;

    public Consumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "wangyulin-test-host:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer, byte[]> records = consumer.poll(1000);
        for (ConsumerRecord<Integer, byte[]> record : records) {
            byte[] origin = record.value();
            SettlementMsg data = (SettlementMsg) SerializationUtils.deserialize(origin);
            //System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
            System.out.println("接收到的信息 : " + data);
        }
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}
