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
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        props.put("message.send.max.retries", "10");
        props.put("retry.backoff.ms", "500");
        //props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.put("value.serializer", "com.wyl.kafka.order.SettlementMsgSerializer");
        //props.put("serializer.class", "kafka.serializer.StringEncoder");
        //props.put("bootstrap.servers", "c3-hadoop-tst-st06.bj:21500");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        for (long nEvents = 0; nEvents < 100; nEvents++) {
            long runtime = new Date().getTime();
            String ip = "192.168.2." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String, String> data = new KeyedMessage<String, String>("miui_theme_test_01", ip, msg);
            try {
                producer.send(data);
            } catch (FailedToSendMessageException e) {
                e.printStackTrace();
                //对发送失败的消息进行处理，可以重新发送，或者忽略
            }
        }
        producer.close();
    }

}
