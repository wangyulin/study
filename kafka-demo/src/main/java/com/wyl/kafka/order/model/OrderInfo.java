package com.wyl.kafka.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by wangyulin on 09/03/2017.
 */
@Data
public class OrderInfo implements Serializable {

    /** 订单ID*/
    private String orderId;

    /** 订单类型：主题、字体、壁纸、铃声等*/
    private String orderType;

    /** 订单包含product的总量*/
    //private long amount;

    /** 订单创建时间*/
    private long createTime;

    /** 订单更新时间*/
    private long updateTime;

    /** 订单支付时间 */
    private long payTime;

    /** 订单支付状态
     * 0:normal;
     * 1:wait_to_pay;
     * 2:success_paid;
     * 3:fail_not_paid
     */
    private String status;

    /** 订单支付渠道: 1:mini; 2:vip虚拟币 */
    private String payChannel;

    /** 订单包含的产品类型:主题、字体、动态壁纸、铃声等*/
    private String productType;

    /** 订单产品id,与assemblyId对应*/
    private String productId;

    /** 订单产品名称,与assemblyName对应*/
    private String productName;

    /** 订单产品版本*/
    //private Long productVersion;

    /** 订单产品版本名称*/
    private String versionName;

    /** 订单价格 *100 单位：分 */
    private long priceOfCent;

    /** 设计师miid*/
    private long designerMiid;
    /** 用户miid*/
    //private long userMiid;
    /** 用户imei*/
    //private String imei;

    /** 用户订单创建设备apkChannel*/
    //private String apkChannel;

    /** 用户订单创建设备romChannel*/
    //private String romChannel;

    /** 用户订单创建设备romVersion*/
    //private String romVersion;

    /** 用户订单处理服务器地区信息*/
    //private String serverRegion;

    /** 用户订单创建客户端地区信息*/
    //private String clientRegin;
}
