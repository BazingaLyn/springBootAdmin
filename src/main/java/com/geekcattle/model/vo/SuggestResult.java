package com.geekcattle.model.vo;

import com.alibaba.fastjson.JSON;

/**
 * 搜索建议结果
 *
 * @author liguolin
 * @create 2018-01-05 18:39
 **/
public class SuggestResult {

    /**
     * 搜索核心关键字
     */
    private Integer keyword;

    /**
     * 核心关键字段
     */
    private String mainSuggestKeyword;


    /**
     * 额外的建议关键词
     */
    private String extSuggestKeyword;

    public Integer getKeyword() {
        return keyword;
    }

    public void setKeyword(Integer keyword) {
        this.keyword = keyword;
    }

    public String getMainSuggestKeyword() {
        return mainSuggestKeyword;
    }

    public void setMainSuggestKeyword(String mainSuggestKeyword) {
        this.mainSuggestKeyword = mainSuggestKeyword;
    }

    public String getExtSuggestKeyword() {
        return extSuggestKeyword;
    }

    public void setExtSuggestKeyword(String extSuggestKeyword) {
        this.extSuggestKeyword = extSuggestKeyword;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
