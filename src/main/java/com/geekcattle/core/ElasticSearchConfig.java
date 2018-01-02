package com.geekcattle.core;

import com.geekcattle.elasticsearch.support.impl.ElasticSearchIndexManagerApiImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticsearch索引配置类
 *
 * @author liguolin
 * @create 2018-01-02 11:22
 **/
@Configuration
public class ElasticSearchConfig {


    @Value("${es.host}")
    private String host;
    @Value("${es.port}")
    private Integer port;
    @Value("${es.elasticsearchNodeName}")
    private String nodeName;

    @Bean(name = "esIndexManager")
    public ElasticSearchIndexManagerApiImpl getElasticSearchIndexManagerApi(){

        ElasticSearchIndexManagerApiImpl elasticSearchIndexManagerApi = new ElasticSearchIndexManagerApiImpl();

        elasticSearchIndexManagerApi.setHost(host);
        elasticSearchIndexManagerApi.setPort(port);
        elasticSearchIndexManagerApi.setClusterName(nodeName);

        return elasticSearchIndexManagerApi;

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
}
