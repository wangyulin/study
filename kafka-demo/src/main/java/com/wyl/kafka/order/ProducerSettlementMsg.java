package com.wyl.kafka.order;

import kafka.common.FailedToSendMessageException;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.commons.lang.SerializationUtils;

import java.util.*;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class ProducerSettlementMsg {

    public static void main(String[] args) {

        Random rnd = new Random();
        Properties props = new Properties();
        props.put("metadata.broker.list", "10.108.44.42:21500,10.108.45.42:21500,10.108.46.42:21500,10.108.47.42:21500,10.108.42.43:21500");
        props.put("request.required.acks", "1");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("serializer.class", "kafka.serializer.DefaultEncoder");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, byte[]> producer = new Producer<String, byte[]>(config);

        for (long nEvents = 0; nEvents < 100000; nEvents++) {
            byte [] message = SerializationUtils.serialize(new SettlementMsg());
            KeyedMessage<String, byte[]> data = new KeyedMessage<String, byte[]>("miui_theme_test_01", nEvents + "", message);
            try {
                producer.send(data);
            } catch (FailedToSendMessageException e) {
                e.printStackTrace();
                //对发送失败的消息进行处理，可以重新发送，或者忽略
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }

}
