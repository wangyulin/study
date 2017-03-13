package com.wyl.kafka.order;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.apache.commons.lang.SerializationUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.*;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class ProducerSettlementMsg {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // serializer.class为消息的序列化类
        //props.put("value.serializer", "com.wyl.kafka.order.SettlementMsgSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        // 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
        //props.put("metadata.broker.list", "c3-hadoop-tst-st06.bj:21500");
        //props.put("bootstrap.servers", "wangyulin-test-host:9092");
        //props.put("bootstrap.servers", "wangyulin-test-host:9092");
        //props.put("metadata.broker.list", "wangyulin-test-host:9092");

        props.put("bootstrap.servers", "10.108.44.42:21500,10.108.45.42:21500,10.108.46.42:21500,10.108.47.42:21500,10.108.42.43:21500");
        props.put("metadata.broker.list", "10.108.44.42:21500,10.108.45.42:21500,10.108.46.42:21500,10.108.47.42:21500,10.108.42.43:21500");

        //props.put("bootstrap.servers", "c3-hadoop-tst-st06.bj:21500");
        // ACK机制, 消息发送需要kafka服务端确认
        props.put("request.required.acks", "1");

        ProducerConfig conf = new ProducerConfig(props);
        //Producer<String, byte[]> producer = new Producer<String, byte[]>(conf);
        KafkaProducer<String, byte[]> producer = new KafkaProducer<String,byte[]>(props);

        //KafkaProducer<String, SettlementMsg> producer = new KafkaProducer<String, SettlementMsg>(props);

        for (int i = 0; i < 1000; i++) {

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setAmount(i);
            orderInfo.setApkChannel(i + "");
            orderInfo.setImei(UUID.randomUUID().toString());
            orderInfo.setPayTime(new Date().getTime());
            orderInfo.setPrice(i * 100);
            orderInfo.setUpdateTime(new Date().getTime());
            orderInfo.setOrderType("ZHUTI");
            orderInfo.setPayChannel(i%2==0? "mibi":"vip");

            ProductInfo productInfo = new ProductInfo();
            productInfo.setPrice(i*100);

            SettlementMsg settlementMsg = new SettlementMsg();
            settlementMsg.setOrderInfo(orderInfo);
            settlementMsg.setProductInfo(productInfo);
            byte[] bytes = SerializationUtils.serialize(settlementMsg);
            //KeyedMessage<String, byte[]> data = new KeyedMessage<String, byte[]>("miui_theme_test", i + "", bytes);

            producer.send(new ProducerRecord<String,byte[]>("miui_theme_test_01",bytes), new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    SettlementMsg message = (SettlementMsg) SerializationUtils.deserialize(bytes);
                    System.out.println(message);
                    if (exception != null)
                        exception.printStackTrace();
                    //System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*producer.send(new ProducerRecord<String, SettlementMsg>("miui_theme_test", i + "", settlementMsg),
                    new Callback() {
                        public void onCompletion(RecordMetadata metadata, Exception e) {
                            if (e != null) {
                                e.printStackTrace();
                            }
                            System.out.println("Sent:" + settlementMsg.toString());
                        }
                    });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }
    }

}
