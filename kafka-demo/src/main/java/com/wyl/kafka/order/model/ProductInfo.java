package com.wyl.kafka.order.model;

import com.wyl.kafka.order.model.DividedInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyulin on 09/03/2017.
 */
@Data
public class ProductInfo implements Serializable {
    private long local_id;

    /** 等同于productId*/
    //private String assemblyId;

    //private String assemblyName;

    //private long price;

    /** 上传主题的设计师的开发者miid*/
    private long designerMiid;

    //private long lastVersion;

    /** 分成类型*/
    private String divideType;

    /** 分成开始时间*/
    private long divideStartTime;

    /** 灵活分成规则*/
    private List<DividedInfo> dividedInfoList;
}
