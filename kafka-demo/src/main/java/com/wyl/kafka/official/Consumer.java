package com.wyl.kafka.official;

/*import com.wyl.kafka.order.SettlementMsg;
import kafka.utils.ShutdownableThread;
import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;*/
/**
 * Created by wangyulin on 13/03/2017.
 */
/*

public class Consumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, byte[]> consumer;
    private final String topic;

    public Consumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.ZK_SERVERS);
        props.put("bootstrap.servers", KafkaProperties.KAFKA_SERVER_URL);
        props.put("group.id", "DemoConsumer");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

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
*/
