package com.wyl.kafka.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wangyulin on 10/03/2017.
 */
@Data
public class DividedInfo implements Serializable {
    /** 对应assemblyId*/
    private long productId;

    /** 对应assemblyId的owner developerId*/
    private long ownerDeveloperMiid;

    /** 设计师类型 0; 1; 2*/
    //private long developerType;

    /** 对应assemblyId的owner nick name*/
    private String ownerNickName;

    /** owner设置的分成方的 developerId*/
    private long partnerDesignerMiid;

    /** owner设置的分成方的 miid*/
    //private long partnerMiid;

    /** owner设置的分成方的 nick name*/
    private String partnerNickName;

    /** 分成周期: 1:owner与平台使用该类型; 2:从设置日期永久分成; 3:设置一个将来的时间段*/
    private String validPeriod;

    /** 分成比例*/
    private int shareInPercent;
}
