package com.geekcattle.aijia.domain;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

/**
 * @Author lgl
 * @time 2017/11/17 10:53
 * @desc
 */
public class Activity1219Info {

    private BigDecimal totalAjbAmount;

    private Integer joinDayCount;

    private String beginTime;

    private String endTime;

    public BigDecimal getTotalAjbAmount() {
        return totalAjbAmount;
    }

    public void setTotalAjbAmount(BigDecimal totalAjbAmount) {
        this.totalAjbAmount = totalAjbAmount;
    }

    public Integer getJoinDayCount() {
        return joinDayCount;
    }

    public void setJoinDayCount(Integer joinDayCount) {
        this.joinDayCount = joinDayCount;
        if(0 == joinDayCount){
            this.totalAjbAmount = BigDecimal.ZERO;
        }
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
