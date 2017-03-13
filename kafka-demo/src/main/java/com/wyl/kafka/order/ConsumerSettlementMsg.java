package com.wyl.kafka.order;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.commons.lang.SerializationUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class ConsumerSettlementMsg {

    public static final int numThreads = 10;
    public static final String topic = "miui_theme_test_01";
    public static ExecutorService executor;

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("group.id", "1");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // serializer.class为消息的序列化类
        props.put("value.deserializer", "com.wyl.kafka.order.SettlementMsgDeserializer");
        // 配置metadata.broker.list, 为了高可用, 最好配两个broker实例
        //props.put("metadata.broker.list", "c3-hadoop-tst-st06.bj:21500");
        //props.put("bootstrap.servers", "wangyulin-test-host:9092");
        //props.put("metadata.broker.list", "wangyulin-test-host:9092");
        //props.put("zookeeper.connect", "wangyulin-test-host:2181");

        props.put("zookeeper.connect", "10.108.83.16:11000,10.108.83.17:11000,10.108.83.24:11000/kafka/c3tst-staging");
        props.put("metadata.broker.list", "10.108.44.42:21500,10.108.45.42:21500,10.108.46.42:21500,10.108.47.42:21500,10.108.42.43:21500");

        ConsumerConfig conf = new ConsumerConfig(props);

        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(conf);
        //设置处理消息线程数，线程数应小于等于partition数量，若线程数大于partition数量，则多余的线程则闲置，不会进行工作
        //key:topic名称 value:线程数
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        //声明一个线程池，用于消费各个partition
        ExecutorService executor=Executors.newFixedThreadPool(1);
        //获取对应topic的消息队列
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        for (final KafkaStream stream : streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            //有信息则消费，无信息将会阻塞
            while (it.hasNext()){
                SettlementMsg message=null;
                try {
                    //将字节码反序列化成相应的对象
                    byte[] bytes=it.next().message();
                    SettlementMsg msg = (SettlementMsg) SerializationUtils.deserialize(bytes);
                    System.out.println("===> " + msg.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                //调用自己的业务逻辑
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*executor.execute(new Runnable() {
                @Override
                public void run() {

                }
            });*/
        }


        /*ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(conf);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(numThreads));//KafkaStream数量与线程数量一一对应
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        // now launch all the threads
        executor = Executors.newFixedThreadPool(numThreads);
        // now create an object to consume the messages
        int threadNumber = 0;
        for (final KafkaStream stream : streams) {
            executor.submit(new ConsumerTest(stream, threadNumber));
            threadNumber++;
        }*/

        /*KafkaConsumer<String, SettlementMsg> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("miui_theme_test"));

        while (true) {
            ConsumerRecords<String, SettlementMsg> records = consumer.poll(100);
            for (final ConsumerRecord<String, SettlementMsg> record : records) {
                System.out.println("Receive: " + record.value().getOrderInfo().getPrice());
            }
        }*/
    }

    //处理message的线程
    private static class ConsumerTest implements Runnable {
        private KafkaStream stream;
        private int threadNumber;

        public ConsumerTest(KafkaStream stream, int threadNumber) {
            this.threadNumber = threadNumber;
            this.stream = stream;
        }

        public void run() {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                System.out.println("Thread " + threadNumber + ": " + new String(it.next().message()));
            }
            System.out.println("Shutting down Thread: " + threadNumber);
        }
    }

}
