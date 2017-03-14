package com.wyl.kafka.order.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Created by wangyulin on 09/03/2017.
 */
@Data
@Slf4j
public class SettlementMsg implements Serializable{
    /** 订单信息*/
    private OrderInfo orderInfo;

    /** 产品信息*/
    private ProductInfo productInfo;
}
