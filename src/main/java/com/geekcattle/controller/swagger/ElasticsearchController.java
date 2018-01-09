package com.geekcattle.controller.swagger;

import com.geekcattle.elasticsearch.support.impl.ElasticSearchIndexManagerApiImpl;
import com.geekcattle.model.vo.CreateIndexParams;
import com.geekcattle.model.vo.EsRequestParams;
import com.geekcattle.model.vo.SetMappingParams;
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
@Api(description = "搜索索引API")
public class ElasticsearchController {

    @Resource
    @Qualifier(value = "esIndexManager")
    private ElasticSearchIndexManagerApiImpl elasticSearchIndexManagerApi;


    @ApiOperation(value="创建索引")
    @RequestMapping(value = "createIndex",method = RequestMethod.POST)
    public ReturnT<Boolean> createIndex(@RequestBody CreateIndexParams createIndexParams){

        ReturnT<Boolean> booleanReturnT = new ReturnT<>();

        Boolean createFlag = elasticSearchIndexManagerApi.createIndex(createIndexParams.getIndexName(),createIndexParams.getShardsNum(),createIndexParams.getReplicas());

        booleanReturnT.setCode(200);
        booleanReturnT.setContent(createFlag);
        return booleanReturnT;
    }


    @ApiOperation(value="删除索引")
    @RequestMapping(value = "deleteIndex",method = RequestMethod.POST)
    public ReturnT<Boolean> deleteIndex(@RequestBody String indexName){

        ReturnT<Boolean> booleanReturnT = new ReturnT<>();

        Boolean createFlag = elasticSearchIndexManagerApi.deleteIndex(indexName);

        booleanReturnT.setCode(200);
        booleanReturnT.setContent(createFlag);
        return booleanReturnT;
    }

    @ApiOperation(value="删除某个索引某个type下的某个id数据")
    @RequestMapping(value = "deleteAllData",method = RequestMethod.POST)
    public ReturnT<Boolean> deleteById(@RequestBody EsRequestParams esRequestParams){

        ReturnT<Boolean> booleanReturnT = new ReturnT<>();

        Boolean createFlag = elasticSearchIndexManagerApi.deleteById(esRequestParams.getIndex(),esRequestParams.getType(),esRequestParams.getId());

        booleanReturnT.setCode(200);
        booleanReturnT.setContent(createFlag);
        return booleanReturnT;
    }

    /**
     * 设置mapping 的时候，是PUT 请求体是JSON http:ip:9200/index[index不能提前创建好]
     * @param setMappingParams
     * @return
     */
    @ApiOperation(value="设置mapping")
    @RequestMapping(value = "setMapping",method = RequestMethod.POST)
    public ReturnT<String> setMapping(@RequestBody SetMappingParams setMappingParams){

        ReturnT<String> booleanReturnT = new ReturnT<>();

        elasticSearchIndexManagerApi.setMappingInfo(setMappingParams.getIndex(),setMappingParams.getType(),setMappingParams.getMappingJsonStr());

        booleanReturnT.setCode(200);
        booleanReturnT.setContent(null);
        return booleanReturnT;
    }












}
