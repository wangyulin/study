package com.wyl.repository;

import com.wyl.ApplicationServiceBoot;
import com.wyl.model.Name;
import com.wyl.pool.model.User;
import com.wyl.model.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangyulin on 03/03/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationServiceBoot.class})
public class NameRepositoryTest {

    @Autowired
    private NameRepository nameRepository;

    private Name name;

    @Before
    public void createName() {
        if(name != null) {
            return;
        }
        name = new Name();
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setAge(20);
        user.setGender(Gender.MALE);
        Name name = new Name();
        name.setName("wyl");
        name.setUser(user);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        nameRepository.save(name);
    }

    @Test
    public void testFindOne() {
        Name name1 = nameRepository.findOne(1L);
        System.out.println(name1.getUser().getGender());
        //assertThat(user.getUuid()).isEqualTo(u1.getUuid());

        //Name name2 = nameRepository.findOne(name.getId());
        //System.out.println(name2.getUser()+ "   " + u2.getCreateTime() + "   " + u2.getUpdateTime());
    }

}
