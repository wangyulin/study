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
        props.put("zookeeper.connect", "10.108.83.16:11000,10.108.83.17:11000,10.108.83.24:11000/kafka/c3tst-staging");
        props.put("group.id", "group_id_01");
        props.put("key.deserializer.class", "kafka.serializer.StringDecoder");
        props.put("deserializer.class", "kafka.serializer.DefaultDecoder");

        ConsumerConnector consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                new ConsumerConfig(props));
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
        }
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
                SettlementMsg settlementMsg = (SettlementMsg) SerializationUtils.deserialize(it.next().message());
                System.out.println("Thread " + threadNumber + ": " + settlementMsg);
            }
            System.out.println("Shutting down Thread: " + threadNumber);
        }
    }
}
