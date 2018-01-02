package com.geekcattle.elasticsearch.support.api;

/**
 * elasticsearch 索引管理
 *
 * @author liguolin
 * @create 2017-12-29 15:02
 **/
public interface ElasticSearchIndexManagerApi {

    /**
     *
     * @param indexName 索引名
     * @param shardsNum 分片数
     * @param replicas 副本数
     * @return
     */
    boolean createIndex(String indexName,Integer shardsNum,Integer replicas);


    /**
     *
     * @param indexName
     * @return
     */
    boolean existIndex(String indexName);

    /**
     *
     * @param indexName
     * @return
     */
    boolean deleteIndex(String indexName);


    /**
     * 设置mapping配置文件
     * @param index
     * @param type
     * @param mappingJsonStr
     */
    void setMappingInfo(String index,String type,String mappingJsonStr);





}
