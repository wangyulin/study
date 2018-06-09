package com.wyl.repository;

import com.wyl.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangyulin on 02/03/2017.
 */
public interface NameRepository extends JpaRepository<Name, Long> {

}
