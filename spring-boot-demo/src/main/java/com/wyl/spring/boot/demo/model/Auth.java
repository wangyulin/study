package com.wyl.spring.boot.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wangyulin on 09/05/2017.
 */
@Data
@Entity
@Table(name = "auth")
public class Auth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private String productType;

    private String productId;

}
