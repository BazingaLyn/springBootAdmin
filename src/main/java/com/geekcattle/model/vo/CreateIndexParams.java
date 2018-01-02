package com.geekcattle.model.vo;

/**
 * 创建elasticsearch入参
 *
 * @author liguolin
 * @create 2018-01-02 14:53
 **/
public class CreateIndexParams {

    private String indexName;
    private Integer shardsNum;
    private Integer replicas;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Integer getShardsNum() {
        return shardsNum;
    }

    public void setShardsNum(Integer shardsNum) {
        this.shardsNum = shardsNum;
    }

    public Integer getReplicas() {
        return replicas;
    }

    public void setReplicas(Integer replicas) {
        this.replicas = replicas;
    }
}
