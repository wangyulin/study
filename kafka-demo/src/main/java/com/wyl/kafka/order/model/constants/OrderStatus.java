package com.wyl.kafka.order.model.constants;

/**
 * Created by wangyulin on 15/03/2017.
 */
public enum  OrderStatus {
    /** 订单支付状态
     * 0:normal;
     * 1:wait_to_pay;
     * 2:success_paid;
     * 3:fail_not_paid
     */
    NORMAL, WAIT_TO_PAY, SUCCESS_PAID, FAIL_NOT_PAID

}
