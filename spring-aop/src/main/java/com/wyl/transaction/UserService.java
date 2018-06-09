package com.wyl.transaction;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangyulin on 14/02/2018.
 */
public interface UserService {

    @Transactional
    void hello();

}
