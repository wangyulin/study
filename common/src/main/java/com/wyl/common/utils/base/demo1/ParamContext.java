package com.wyl.common.utils.base.demo1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyulin on 28/12/2017.
 */
public class ParamContext {
    private Map<String,Object> datas= new HashMap<>();
    public ParamContext(Object...params){
        if(params==null||params.length==0){
            return;
        }
        for(int i=0;i<params.length;){
            datas.put((String) params[i], params[i+1]);
            i+=2;
        }
    }
    @SuppressWarnings("unchecked")
    public <R> R get(String key){
        return (R)datas.get(key);
    }
}
