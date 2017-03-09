package com.wyl.kafka.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class SettlementMsgSerializer implements Serializer<SettlementMsg> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public byte[] serialize(String arg0, SettlementMsg arg1) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

}
