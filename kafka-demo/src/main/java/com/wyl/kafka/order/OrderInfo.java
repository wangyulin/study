package com.wyl.kafka.order;

/**
 * Created by wangyulin on 09/03/2017.
 */
public class OrderInfo {

    /** 订单ID*/
    private String orderId;

    /** 订单类型：主题、字体、壁纸、铃声等*/
    private String orderType;

    /** 订单包含product的总量*/
    private long amount;

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
    private byte status;

    /** 订单支付渠道:mini、vip */
    private String payChannel;

    /** 订单包含的产品类型:主题、字体、动态壁纸、铃声等*/
    private byte productType;

    /** 订单产品id,与assemblyId对应*/
    private String productId;

    /** 订单产品名称,与assemblyName对应*/
    private String productName;

    /** 订单产品版本*/
    private Long productVersion;

    /** 订单产品版本名称*/
    private String versionName;

    /** 订单价格*/
    private long price;

    /** 设计师ID*/
    private long developerId;
    /** 用户id*/
    private long userId;
    /** 用户imei*/
    private String imei;

    /** 用户订单创建设备apkChannel*/
    private String apkChannel;

    /** 用户订单创建设备romChannel*/
    private String romChannel;

    /** 用户订单创建设备romVersion*/
    private String romVersion;

    /** 用户订单处理服务器地区信息*/
    private String serverRegion;

    /** 用户订单创建客户端地区信息*/
    private String clientRegin;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public byte getProductType() {
        return productType;
    }

    public void setProductType(byte productType) {
        this.productType = productType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(Long productVersion) {
        this.productVersion = productVersion;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getApkChannel() {
        return apkChannel;
    }

    public void setApkChannel(String apkChannel) {
        this.apkChannel = apkChannel;
    }

    public String getRomChannel() {
        return romChannel;
    }

    public void setRomChannel(String romChannel) {
        this.romChannel = romChannel;
    }

    public String getRomVersion() {
        return romVersion;
    }

    public void setRomVersion(String romVersion) {
        this.romVersion = romVersion;
    }

    public String getServerRegion() {
        return serverRegion;
    }

    public void setServerRegion(String serverRegion) {
        this.serverRegion = serverRegion;
    }

    public String getClientRegin() {
        return clientRegin;
    }

    public void setClientRegin(String clientRegin) {
        this.clientRegin = clientRegin;
    }
}
