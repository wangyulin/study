package com.wyl.kafka.order;


import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.wyl.kafka.order.model.SettlementMsg;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.DefaultDecoder;
import kafka.serializer.StringDecoder;
import org.apache.commons.lang.SerializationUtils;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by wangyulin on 09/03/2017.
 */
public class ConsumerSettlementMsg {

    public static final int numThreads = 10;
    public static final String topic = "miui_theme_test_02";
    public static ExecutorService executor;

    public static void main(String[] args) {
        Properties props = new Properties();
        /*props.put("zookeeper.connect", "10.108.83.16:11000,10.108.83.17:11000,10.108.83.24:11000/kafka/c3tst-staging");*/
        props.put("zookeeper.connect", "wangyulin-test-host:2181");
        props.put("group.id", "group_id_01");
        props.put("key.deserializer.class", StringDecoder.class.getName());
        props.put("deserializer.class", DefaultDecoder.class.getName());

        props.put("fetch.message.max.bytes", "314005728");//3M 3145728，设置得大一点避免无法拉去topic中比较大的message
        props.put("zookeeper.session.timeout.ms", "30000");//不要设置得太小，否则会频繁触发rebalance
        props.put("rebalance.backoff.ms", "10000");//两次rebalance尝试之间的时间间隔，建议不要太小
        props.put("rebalance.max.retries", "10");//rebalance的尝试次数，需要保证rebalance.max.retries * rebalance.backoff.ms > zookeeper.session.timeout.ms


        final ActorSystem system = ActorSystem.create("kafka_consumer");
        final ActorRef orderActor = system.actorOf(Props.create(OrderActor.class), "order_consumer");

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
