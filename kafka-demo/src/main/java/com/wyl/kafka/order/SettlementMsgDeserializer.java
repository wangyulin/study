package com.wyl.kafka.order;

/*import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyl.kafka.demo3.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;*/

/**
 * Created by wangyulin on 09/03/2017.
 */
/*
public class SettlementMsgDeserializer implements Deserializer<SettlementMsg> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public SettlementMsg deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        SettlementMsg settlementMsg = null;
        try {
            settlementMsg = mapper.readValue(arg1, SettlementMsg.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return settlementMsg;
    }

}
*/
