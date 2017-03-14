package com.wyl.kafka.official;

/**
 * Created by wangyulin on 13/03/2017.
 */
public class KafkaProperties {
    public static final String TOPIC = "miui_theme_test_01";
    public static final String KAFKA_SERVER_URL = "10.108.44.42:21500,10.108.45.42:21500,10.108.46.42:21500,10.108.47.42:21500,10.108.42.43:21500";
    public static final String ZK_SERVERS = "10.108.83.16:11000,10.108.83.17:11000,10.108.83.24:11000/kafka/c3tst-staging";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final int KAFKA_PRODUCER_BUFFER_SIZE = 64 * 1024;
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final String TOPIC2 = "topic2";
    public static final String TOPIC3 = "topic3";
    public static final String CLIENT_ID = "SimpleConsumerDemoClient";

    private KafkaProperties() {}
}
