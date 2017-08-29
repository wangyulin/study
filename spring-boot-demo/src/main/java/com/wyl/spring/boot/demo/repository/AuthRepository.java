package com.wyl.spring.boot.demo.repository;

import com.wyl.spring.boot.demo.model.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by wangyulin on 09/05/2017.
 */
@RepositoryRestResource(path = "primary", collectionResourceRel = "data")
public interface AuthRepository extends CrudRepository<Auth, Long> {

}
