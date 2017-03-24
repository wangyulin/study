package com.wyl.kafka.order.model.constants;

/**
 * Created by wangyulin on 15/03/2017.
 */
public enum DivideType {
    /** 三七分成*/
    NORMAL,
    /** 买断，现在基本不用了*/
    BUYOUT,
    /** 不参与分成*/
    NO_DIVIDE,
    /** 永久免费*/
    FREE

}
