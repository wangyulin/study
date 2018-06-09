package com.wyl.repository;

import com.wyl.pool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangyulin on 28/02/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(long id);

    @Transactional
    @Modifying
    @Query("update User u set u.age = ?1 where u.id = ?2")
    void setUserById(int age, Long id);

}
