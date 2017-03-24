package com.wyl.kafka.order;

import akka.actor.UntypedActor;
import com.wyl.kafka.order.model.SettlementMsg;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.apache.commons.lang.SerializationUtils;

/**
 * Created by wangyulin on 22/03/2017.
 */
public class OrderActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if(message instanceof KafkaStream) {
            KafkaStream stream = (KafkaStream) message;
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                SettlementMsg settlementMsg = (SettlementMsg) SerializationUtils.deserialize(it.next().message());
                System.out.println("Thread " + ": " + settlementMsg);
            }
        }
    }
}
