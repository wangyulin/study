package com.wyl.model;

import javax.persistence.*;

/**
 * Created by wangyulin on 26/02/2017.
 */
@Entity(name="name")
public class Name {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    /**
     * @OneToOne：一对一关联
     * cascade：级联配置
     * CascadeType.PERSIST: 级联新建
     * CascadeType.REMOVE : 级联删除
     * CascadeType.REFRESH: 级联刷新
     * CascadeType.MERGE  : 级联更新
     * CascadeType.ALL    : 以上全部四项
     * @JoinColumn:主表外键字段
     * cid：Care所映射的表中的一个字段(会在User表创建一个cid字段,与Care外键关系)
     */
    //@OneToOne(cascade = CascadeType.REFRESH, targetEntity = Name.class, fetch = FetchType.EAGER, optional = false)
    //@JoinColumn(name="name_id")
    @OneToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
