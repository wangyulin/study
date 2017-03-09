package com.wyl.kafka.order;

import java.util.List;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class SettlementMsg {

    /** 区分创建订单(CREATE_ORDER)、支付成功(PAY_SUCCESS)*/
    private String type;

    private OrderInfo orderInfo;

    private List<ProductInfo> productInfos;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }
}
