package com.geekcattle.aijia.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lgl
 * @time 2017/11/17 10:32
 * @desc
 */
public class OmsOrderInfo {

    private String productName;

    private Integer count;

    private BigDecimal totalPrice;

    private String omsOrderNo;

    private Date orderTime;

    private Integer orderStatus;

    private String orderStatusStr;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOmsOrderNo() {
        return omsOrderNo;
    }

    public void setOmsOrderNo(String omsOrderNo) {
        this.omsOrderNo = omsOrderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
        if(null != orderStatus){
            switch (orderStatus){
                // 订单状态,总状态 -1: 付款失败 0:提交订单成功 1:处理中 2:已完成 3:已取消 4:待付款 5:待收货 6:部分付款 7:待施工 8:施工中 9:待退款 10:待发货 11:待结款 12:交易关闭
                case -1 :
                    this.orderStatusStr = "付款失败";
                    break;
                case 0 :
                    this.orderStatusStr = "提交订单成功";
                    break;
                case 1 :
                    this.orderStatusStr = "处理中";
                    break;
                case 2 :
                    this.orderStatusStr = "已完成";
                    break;
                case 3 :
                    this.orderStatusStr = "已取消";
                    break;
                case 4 :
                    this.orderStatusStr = "待付款";
                    break;
                case 5 :
                    this.orderStatusStr = "待收货";
                    break;
                case 6 :
                    this.orderStatusStr = "部分付款";
                    break;
                case 7 :
                    this.orderStatusStr = "待施工";
                    break;
                case 8 :
                    this.orderStatusStr = "施工中";
                    break;
                case 9 :
                    this.orderStatusStr = "待退款";
                    break;
                case 10 :
                    this.orderStatusStr = "待发货";
                    break;
                case 11 :
                    this.orderStatusStr = "待结款";
                    break;
                case 12 :
                    this.orderStatusStr = "交易关闭";
                    break;
            }
        }
    }

    public String getOrderStatusStr() {
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }
}
