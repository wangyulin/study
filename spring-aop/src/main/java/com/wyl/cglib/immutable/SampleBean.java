package com.wyl.cglib.immutable;

import lombok.Data;

/**
 * Created by wangyulin on 12/02/2018.
 */
@Data
public class SampleBean {

    private String value;

    public SampleBean() {
    }

    public SampleBean(String value) {
        this.value = value;
    }

}
