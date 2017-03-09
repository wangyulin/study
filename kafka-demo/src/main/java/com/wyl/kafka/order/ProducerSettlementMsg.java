package com.wyl.kafka.order;

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
        props.put("value.serializer", "com.wyl.kafka.order.SettlementMsgSerializer");
        // 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
        //props.put("metadata.broker.list", "wangyulin-test-host:9092");
        props.put("bootstrap.servers", "wangyulin-test-host:9092");
        // 设置Partition类, 对队列进行合理的划分
        //props.put("partitioner.class", "idoall.testkafka.Partitionertest");
        // ACK机制, 消息发送需要kafka服务端确认
        props.put("request.required.acks", "1");


        KafkaProducer<String, SettlementMsg> producer = new KafkaProducer<String, SettlementMsg>(props);

        for (int i = 0; i < 10; i++) {

            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setAmount(i);
            orderInfo.setApkChannel(i + "");
            orderInfo.setImei(UUID.randomUUID().toString());
            orderInfo.setPayTime(new Date().getTime());
            orderInfo.setPrice(i * 100);
            orderInfo.setUpdateTime(new Date().getTime());
            orderInfo.setOrderType("ZHUTI");

            List<ProductInfo> productInfos = new ArrayList<>();
            ProductInfo productInfo = new ProductInfo();
            productInfo.setPrice(i*100);
            productInfos.add(productInfo);

            SettlementMsg settlementMsg = new SettlementMsg();
            settlementMsg.setType("ZHUTI");
            settlementMsg.setOrderInfo(orderInfo);
            settlementMsg.setProductInfos(productInfos);

            producer.send(new ProducerRecord<String, SettlementMsg>("test002", i + "", settlementMsg),
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
            }

        }

    }

}
