package com.wyl.kafka.order.model;

import com.wyl.kafka.order.model.DividedInfo;
import com.wyl.kafka.order.model.constants.DivideType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangyulin on 09/03/2017.
 */
@Data
public class ProductInfo implements Serializable {
    private long local_id;

    /** 上传主题的设计师的开发者miid*/
    private long designerMiid;

    /** 分成类型 */
    private DivideType divideType;

    /** 分成开始时间*/
    private long divideStartTime;

    /** 灵活分成规则*/
    private List<DividedInfo> dividedInfoList;
}
