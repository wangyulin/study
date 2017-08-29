package com.wyl.repository;

import com.wyl.ApplicationServiceBoot;
import com.wyl.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wangyulin on 28/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationServiceBoot.class})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void createUser() {
        if(user != null) {
            return;
        }
        user = new User();
        user.setUuid("0000001");
        //user.setName("wyl");
        user.setAge(20);
        //user.setCreateTime(new Date());
        //user.setUpdateTime(new Date());
        userRepository.save(user);
    }

    @Test
    public void testFindOne() {
        User u1 = userRepository.findOne(user.getId());
        System.out.println(u1.getAge() + "   " + u1.getCreateTime() + "   " + u1.getUpdateTime());
        assertThat(user.getUuid()).isEqualTo(u1.getUuid());

        userRepository.setUserById(30, u1.getId());
        User u2 = userRepository.findOne(user.getId());
        System.out.println(u2.getAge() + "   " + u2.getCreateTime() + "   " + u2.getUpdateTime());
    }

    @After
    public void deleteUser() {
        userRepository.delete(user.getId());
    }

}
