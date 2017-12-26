package com.geekcattle.model.vo;

import javax.persistence.Transient;

/**
 * 艾佳艾积分搜索参数
 *
 * @author liguolin
 * @create 2017-12-25 16:17
 **/
public class AijiaAjfSearchVo {

    private String key;

    @Transient
    private Integer offset = 0;

    @Transient
    private Integer limit = 10;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
