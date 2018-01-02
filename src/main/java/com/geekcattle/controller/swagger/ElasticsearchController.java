package com.geekcattle.controller.swagger;

import com.geekcattle.elasticsearch.support.impl.ElasticSearchIndexManagerApiImpl;
import com.geekcattle.model.vo.CreateIndexParams;
import com.geekcattle.task.AccountAjfTask;
import com.geekcattle.util.ReturnT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @author liguolin
 * @create 2017-12-26 14:58
 **/
@RestController
@Api("ES服务类")
public class ElasticsearchController {

    @Resource
    @Qualifier(value = "esIndexManager")
    private ElasticSearchIndexManagerApiImpl elasticSearchIndexManagerApi;


    @ApiOperation(value="创建索引")
    @RequestMapping(value = "createIndex")
    public ReturnT<Boolean> createIndex(@RequestBody CreateIndexParams createIndexParams){

        ReturnT<Boolean> booleanReturnT = new ReturnT<>();

        Boolean createFlag = elasticSearchIndexManagerApi.createIndex(createIndexParams.getIndexName(),createIndexParams.getShardsNum(),createIndexParams.getReplicas());

        booleanReturnT.setCode(200);
        booleanReturnT.setContent(createFlag);
        return booleanReturnT;
    }


    @ApiOperation(value="删除索引")
    @RequestMapping(value = "deleteIndex")
    public ReturnT<Boolean> deleteIndex(@RequestBody String indexName){

        ReturnT<Boolean> booleanReturnT = new ReturnT<>();

        Boolean createFlag = elasticSearchIndexManagerApi.deleteIndex(indexName);

        booleanReturnT.setCode(200);
        booleanReturnT.setContent(createFlag);
        return booleanReturnT;
    }









}
