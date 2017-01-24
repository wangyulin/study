package com.wyl.comment.demo1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import com.wyl.comment.SampleDataJpaApplication;
import com.wyl.comment.UserApplication;
import com.wyl.comment.dao.CommentRepository;
import com.wyl.comment.module.Comment;
import com.wyl.comment.module.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by wangyulin on 20/01/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SampleDataJpaApplication.class})
public class CommentRepositoryTests {

    /*@Autowired
    private TestEntityManager entityManager;*/

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testFindByLastName() {
        Comment comment = new Comment();
        comment.setBizId(2);
        comment.setBizType("appstore");
        comment.setContent("OK");
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setDeleted(false);
        comment.setParentId(-1);

        //entityManager.persist(customer);

        Comment one = commentRepository.save(comment);

        //assertThat(findByLastName).extracting(Customer::getLastName).containsOnly(customer.getLastName());
    }
}
