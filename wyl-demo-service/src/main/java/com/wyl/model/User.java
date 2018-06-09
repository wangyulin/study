package com.wyl.pool.model;

import com.wyl.listener.AuditListener;
import com.wyl.model.Gender;
import com.wyl.model.Name;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangyulin on 28/02/2017.
 *
 */
@Entity
@EntityListeners(value={AuditListener.class})
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String uuid;

    @OneToOne(mappedBy="user")
    private Name name;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private int age;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
