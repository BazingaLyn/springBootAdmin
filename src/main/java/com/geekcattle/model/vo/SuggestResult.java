package com.geekcattle.model.vo;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 搜索建议结果
 *
 * @author liguolin
 * @create 2018-01-05 18:39
 **/
public class SuggestResult {


    private Integer code;

    private List<EachSuggestItems> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<EachSuggestItems> getData() {
        return data;
    }

    public void setData(List<EachSuggestItems> data) {
        this.data = data;
    }

    public static class EachSuggestItems {

        /**
         * 搜索核心关键字
         */
        private Integer keyword;

        /**
         * 核心关键字段
         */
        private String mainSuggestKeyword;


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
    }



    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
