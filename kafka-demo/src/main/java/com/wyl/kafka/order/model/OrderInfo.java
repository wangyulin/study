package com.wyl.kafka.order.model;

import com.wyl.kafka.order.model.constants.OrderStatus;
import com.wyl.kafka.order.model.constants.PayChannel;
import com.wyl.kafka.order.model.constants.ProductType;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by wangyulin on 09/03/2017.
 */
@Data
public class OrderInfo implements Serializable {

    /** 订单ID*/
    private String orderId;

    /** 订单创建时间*/
    private long createTime;

    /** 订单更新时间*/
    private long updateTime;

    /** 订单支付时间 */
    private long payTime;

    /** 订单支付状态 */
    private OrderStatus status;

    /** 订单支付渠道 */
    private PayChannel payChannel;

    /** 订单包含的产品类型*/
    private ProductType productType;

    /** 订单产品id,与assemblyId对应*/
    private String productId;

    /** 订单产品名称,与assemblyName对应*/
    private String productName;

    /** 订单产品版本名称*/
    private String versionName;

    /** 订单价格 *100 单位：分 */
    private long priceOfCent;

    /** 设计师miid*/
    private long designerMiid;
}