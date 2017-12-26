package com.geekcattle.aijia.domain;

import com.alibaba.fastjson.JSON;

/**
 * @Author lgl
 * @time 2017/11/16 10:52
 * @desc
 */
public class UserInfo {

    private Integer userId;

    private String mobile;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
