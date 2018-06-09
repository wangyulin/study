package com.wyl.service.impl;

import com.wyl.model.Name;
import com.wyl.service.ExampleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyulin on 27/02/2017.
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    private static Map<String, String> nameStore = new HashMap<>();

    static {
        nameStore.put("1", "Wangyulin");
        nameStore.put("2", "WangGang");
        nameStore.put("3", "ZhangFei");
    }

    @Override
    public String findName(String id) {
        if(nameStore.containsKey(id)) {
            return nameStore.get(id);
        } else {
            return null;
        }
    }

    @Override
    public boolean addNameInfo(Name name) {
//        if(name.getNid() != 0) {
//            nameStore.put(name.getNid() + "", name.getName());
//            ApplicationServiceBoot.eventBus.post(new DataEvent(200));
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }

}
