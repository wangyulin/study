package com.wyl.kafka.order;


import kafka.serializer.Decoder;
import org.apache.commons.lang.SerializationUtils;

/**
 * Created by wangyulin on 09/03/2017.
 */

public class SettlementMsgDeserializer implements Decoder<SettlementMsg> {

    @Override
    public SettlementMsg fromBytes(byte[] bytes) {
        return (SettlementMsg) SerializationUtils.deserialize(bytes);
    }
}
