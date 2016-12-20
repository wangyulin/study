package com.wyl.test.json;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyulin on 19/12/2016.
 */
public class json {
    public static void main(String[] args) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Float.class, new JsonFloatValueProcessor());
        Float f = Float.valueOf(10.653209f);
        Map<String, Float> map = new HashMap<String, Float>();
        map.put("k1", f);
        JSONObject json = JSONObject.fromObject(map);//,config);
        System.out.println(json);
    }
}

class JsonFloatValueProcessor implements JsonValueProcessor {

    public JsonFloatValueProcessor() {
        super();
    }

    @Override
    public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    @Override
    public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    private Object process(Object value){
        if(value instanceof Float) {
            BigDecimal b = new BigDecimal(Float.valueOf((Float)value));
            return b.setScale(3, BigDecimal.ROUND_HALF_UP);
        }
        return value == null ? "" : value.toString();
    }
}