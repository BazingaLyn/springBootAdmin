package com.geekcattle.model.vo;

/**
 * elasticsearch请求的基本参数
 *
 * @author liguolin
 * @create 2018-01-05 15:27
 **/
public class EsRequestParams {

    private String index;

    private String type;

    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
