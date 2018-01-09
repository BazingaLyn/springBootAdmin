package com.geekcattle.elasticsearch.support.impl;

import com.geekcattle.elasticsearch.support.api.ElasticSearchIndexManagerApi;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Response;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.InitializingBean;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * es索引管理API实现
 *
 * @author liguolin
 * @create 2018-01-02 11:05
 **/
public class ElasticSearchIndexManagerApiImpl implements ElasticSearchIndexManagerApi,InitializingBean {


    private String host;
    private Integer port;
    private String clusterName;
    private Client client;
    private IndicesAdminClient indicesAdminClient;

    @Override
    public boolean createIndex(String indexName, Integer shardsNum, Integer replicas) {

        if(!existIndex(indexName)){

            CreateIndexResponse cResponse = indicesAdminClient.prepareCreate(indexName).setSettings(Settings.builder()
                    .put("index.number_of_shards",shardsNum)
                    .put("index.number_of_replicas",replicas)).get();

            return cResponse.isShardsAcked();
        }

        return false;

    }

    @Override
    public boolean existIndex(String indexName) {

        IndicesExistsResponse existsResponse = indicesAdminClient.prepareExists(indexName).get();
        return existsResponse.isExists();
    }

    @Override
    public boolean deleteIndex(String indexName) {

        DeleteIndexResponse dResponse = indicesAdminClient.prepareDelete(indexName).get();

        return dResponse.isAcknowledged();
    }

    @Override
    public void setMappingInfo(String index, String type, String mappingJsonStr) {

        indicesAdminClient.preparePutMapping(index).setType(type).setSource(mappingJsonStr);

    }

    @Override
    public Boolean deleteById(String index, String type,String id) {

        DeleteIndexResponse deleteReponse = indicesAdminClient.prepareDelete(index,type,id).get();
        return deleteReponse.isAcknowledged();

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public IndicesAdminClient getIndicesAdminClient() {
        return indicesAdminClient;
    }

    public void setIndicesAdminClient(IndicesAdminClient indicesAdminClient) {
        this.indicesAdminClient = indicesAdminClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (null == client) {
            Settings settings = Settings.builder().put("cluster.name", clusterName)
//					.put("xpack.security.transport.ssl.enabled", false)
//					.put("xpack.security.user", "elastic:changeme")
                    .put("client.transport.sniff", true).build();
            try {
                client = new PreBuiltTransportClient(settings).addTransportAddress(
                        new TransportAddress(InetAddress.getByName(host), port));

                indicesAdminClient = client.admin().indices();
            } catch (UnknownHostException e) {
            }
        }

    }
}
