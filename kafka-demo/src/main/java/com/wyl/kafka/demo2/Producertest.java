package com.wyl.kafka.demo2;

import java.util.Date;
import java.util.Properties;
import java.text.SimpleDateFormat;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * Created by wangyulin on 08/03/2017.
 */
public class Producertest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("zk.connect", "wangyulin-test-host:2181");
        // serializer.class为消息的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
        props.put("metadata.broker.list", "wangyulin-test-host:9092");
        // 设置Partition类, 对队列进行合理的划分
        //props.put("partitioner.class", "idoall.testkafka.Partitionertest");
        // ACK机制, 消息发送需要kafka服务端确认
        props.put("request.required.acks", "1");

        props.put("num.partitions", "4");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        for (int i = 0; i < 1000; i++) {
            // KeyedMessage<K, V>
            // K对应Partition Key的类型
            // V对应消息本身的类型
            //topic: "test", key: "key", message: "message"
            SimpleDateFormat formatter = new SimpleDateFormat   ("yyyy年MM月dd日 HH:mm:ss SSS");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String str = formatter.format(curDate);

            String msg = "wangyulin " + i + "=" + str;
            String key = i + "";
            producer.send(new KeyedMessage<String, String>("test",key, msg));
        }
    }

}
