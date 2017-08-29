package com.wyl.listener;

import com.wyl.model.User;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by wangyulin on 28/02/2017.
 */
public class AuditListener {

    @PrePersist
    public void prePersist(Object entity) {
        if(entity instanceof User) {
            ((User)entity).setCreateTime(new Date());
            ((User)entity).setUpdateTime(new Date());
        }
    }


    @PreUpdate
    public void preUpdate(Object entity) {
        if(entity instanceof User) {
            ((User)entity).setUpdateTime(new Date());
        }
    }

}
