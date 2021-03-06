package com.wyl.kafka.demo;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangyulin on 29/09/2016.
 */
public class TestKafkaConsumer {
    private ConsumerConfig config;
    private String topic;
    private int partitionNum;
    private MessageExecutor executor;
    private ConsumerConnector connector;
    private ExecutorService threadPool;

    private TestKafkaConsumer(String topic, int partitionNum, MessageExecutor executor) throws IOException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResourceAsStream("consumer.properties"));
        config = new ConsumerConfig(props);
        this.topic = topic;
        this.partitionNum = partitionNum;
        this.executor = executor;
    }

    private void
    start() {
        connector = Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topics = new HashMap<>();
        topics.put(topic, partitionNum);
        Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topics);
        List<KafkaStream<byte[], byte[]>> partitions = streams.get(topic);
        threadPool = Executors.newFixedThreadPool(partitionNum);
        for (KafkaStream<byte[], byte[]> partition : partitions) {
            threadPool.execute(new MessageRunner(partition));
        }
    }

    private class MessageRunner implements Runnable {
        private KafkaStream<byte[], byte[]> partition;

        MessageRunner(KafkaStream<byte[], byte[]> partition) {
            this.partition = partition;
        }

        @Override
        public void run() {
            ConsumerIterator<byte[], byte[]> it = partition.iterator();
            while (it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> item = it.next();
                executor.execute(new String(item.message()));
            }
        }
    }

    interface MessageExecutor {
        void execute(String message);
    }

    public static void main(String[] args) {
        TestKafkaConsumer consumer;
        try {
            MessageExecutor executor = System.out::println;
            consumer = new TestKafkaConsumer("ATopic", 2, executor);
            consumer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}