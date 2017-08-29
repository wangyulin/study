package com.wyl.repository;

import com.wyl.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wangyulin on 02/03/2017.
 */
public interface NameRepository extends JpaRepository<Name, Long> {

}
