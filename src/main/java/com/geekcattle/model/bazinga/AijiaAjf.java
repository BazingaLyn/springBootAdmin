package com.geekcattle.model.bazinga;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 艾佳艾积分
 *
 * @author liguolin
 * @create 2017-12-25 15:49
 **/
public class AijiaAjf {

    private String username;

    private Integer userId;

    private String mobile;

    private String aladdinOrderIds;

    private BigDecimal ajfBalanceAmount;

    private BigDecimal artConsumerAmount;

    private BigDecimal activity1219Amount;

    private BigDecimal chargeRMBTotalAmount;

    private Date endDate;

    private Integer state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAladdinOrderIds() {
        return aladdinOrderIds;
    }

    public void setAladdinOrderIds(String aladdinOrderIds) {
        this.aladdinOrderIds = aladdinOrderIds;
    }

    public BigDecimal getAjfBalanceAmount() {
        return ajfBalanceAmount;
    }

    public void setAjfBalanceAmount(BigDecimal ajfBalanceAmount) {
        this.ajfBalanceAmount = ajfBalanceAmount;
    }

    public BigDecimal getArtConsumerAmount() {
        return artConsumerAmount;
    }

    public void setArtConsumerAmount(BigDecimal artConsumerAmount) {
        this.artConsumerAmount = artConsumerAmount;
    }

    public BigDecimal getActivity1219Amount() {
        return activity1219Amount;
    }

    public void setActivity1219Amount(BigDecimal activity1219Amount) {
        this.activity1219Amount = activity1219Amount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public BigDecimal getChargeRMBTotalAmount() {
        return chargeRMBTotalAmount;
    }

    public void setChargeRMBTotalAmount(BigDecimal chargeRMBTotalAmount) {
        this.chargeRMBTotalAmount = chargeRMBTotalAmount;
    }
}
