package com.wyl.kafka.order;

import kafka.serializer.Encoder;
import org.apache.commons.lang.SerializationUtils;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class SettlementMsgSerializer implements Encoder<SettlementMsg> {

    @Override
    public byte[] toBytes(SettlementMsg settlementMsg) {
        return SerializationUtils.serialize(settlementMsg);
    }
}
