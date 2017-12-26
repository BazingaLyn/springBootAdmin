package com.geekcattle.aijia.domain;

import com.alibaba.fastjson.JSON;

/**
 * @Author lgl
 * @time 2017/11/16 10:16
 * @desc
 */
public class BaseAccountInfo {

    private Integer id;

    private Integer userId;

    private Integer accountType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
