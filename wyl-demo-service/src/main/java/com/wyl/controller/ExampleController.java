package com.wyl.controller;

import com.wyl.model.Name;
import com.wyl.model.User;
import com.wyl.model.constants.Gender;
import com.wyl.repository.NameRepository;
import com.wyl.repository.UserRepository;
import com.wyl.service.ExampleService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wangyulin on 25/02/2017.
 */
@RestController
//@EnableDiscoveryClient
@RequestMapping(value="/")
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NameRepository nameRepository;

    @CrossOrigin(origins = {""})
    @RequestMapping(value = "/name/{id}", method = RequestMethod.GET)
    public String findName(@PathVariable String id) {
        addUser();
        //ValueOperations<String, String> ops = this.template.opsForValue();
        /*String res = ops.get(id);
        if(StringUtils.isNotBlank(res)){
            return res;
        } else {*/
            String r = exampleService.findName(id);
            /*if(StringUtils.isNotBlank(r)) {
                ops.set(id, r);
            }*/
            return r;
        //}
    }

    @RequestMapping(value = "/name/add", method = RequestMethod.POST)
    public boolean addNameInfo(@RequestBody() Name name) {
        return exampleService.addNameInfo(name);
    }

    public void addUser() {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setAge(20);
        user.setGender(Gender.MALE);
        Name name = new Name();
        name.setName("wyl");
        //user.setName(name);
        name.setUser(user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //userRepository.save(user);
        nameRepository.save(name);
    }

}
