package com.wyl.spring.boot.demo.repository;

import com.wyl.spring.boot.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by wangyulin on 27/03/2017.
 */
@RepositoryRestResource(path = "primary", collectionResourceRel = "data")
public interface UserRepository extends CrudRepository<User, Long> {

}
