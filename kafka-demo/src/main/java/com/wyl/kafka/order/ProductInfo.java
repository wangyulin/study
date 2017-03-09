package com.wyl.kafka.order;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class ProductInfo {
    private long local_id;

    /** 等同于productId*/
    private String assemblyId;
    private String assemblyName;
    private long price;
    /** 上传主题的设计师的开发者ID*/
    private long publisherPrincipalId;
    private long lastVersion;
    /** 分成类型*/
    private String divideType;
    /** 分成开始时间*/
    private long divideStartTime;

    public long getLocal_id() {
        return local_id;
    }

    public void setLocal_id(long local_id) {
        this.local_id = local_id;
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getAssemblyName() {
        return assemblyName;
    }

    public void setAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPublisherPrincipalId() {
        return publisherPrincipalId;
    }

    public void setPublisherPrincipalId(long publisherPrincipalId) {
        this.publisherPrincipalId = publisherPrincipalId;
    }

    public long getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(long lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getDivideType() {
        return divideType;
    }

    public void setDivideType(String divideType) {
        this.divideType = divideType;
    }

    public long getDivideStartTime() {
        return divideStartTime;
    }

    public void setDivideStartTime(long divideStartTime) {
        this.divideStartTime = divideStartTime;
    }
}
