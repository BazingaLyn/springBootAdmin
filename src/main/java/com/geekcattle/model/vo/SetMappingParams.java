package com.geekcattle.model.vo;

import com.alibaba.fastjson.JSON;

/**
 * 设置mapping参数
 *
 * @author liguolin
 * @create 2018-01-05 13:24
 **/
public class SetMappingParams {

    private String index;
    private String type;
    private String mappingJsonStr;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMappingJsonStr() {
        return mappingJsonStr;
    }

    public void setMappingJsonStr(String mappingJsonStr) {
        this.mappingJsonStr = mappingJsonStr;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
