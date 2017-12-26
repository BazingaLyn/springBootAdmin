package com.geekcattle.elasticsearch.support.impl;

import com.alibaba.fastjson.JSON;
import com.geekcattle.config.utils.FileToJsonStrUtils;
import com.geekcattle.config.utils.Page;
import com.geekcattle.elasticsearch.support.api.ElasticSearchApi;
import com.geekcattle.elasticsearch.support.conf.ElasticSearchConfig;
import com.google.common.base.Strings;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequestBuilder;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElasticSearchApiImpl<T> implements ElasticSearchApi<T>,ElasticSearchConfig {

    private final static Logger logger = LoggerFactory.getLogger(ElasticSearchApiImpl.class);
	

	private String index;
	private String type;
	private Client client;

	public ElasticSearchApiImpl(String host, Integer port, String clusterName) {
		if (null == client) {
			Settings settings = Settings.builder().put("cluster.name", clusterName)
//					.put("xpack.security.transport.ssl.enabled", false)
//					.put("xpack.security.user", "elastic:changeme")
					.put("client.transport.sniff", true).build();
			try {
				client = new PreBuiltTransportClient(settings).addTransportAddress(
						new TransportAddress(InetAddress.getByName(host), port));
			} catch (UnknownHostException e) {
			}
		}
	}


	public Client getClient() {
		return client;
	}

	public Integer index(T t) {
		if(null != index && null != type){
			IndexResponse indexResponse = client.prepareIndex(index, type).setSource(JSON.toJSONString(t)).get();
			if(indexResponse.getId() != null){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public Integer update(String fieldName,String value, String id) {
		if(null != index && null != type && null != fieldName && !Strings.isNullOrEmpty(id)){
			UpdateRequest updateRequest = new UpdateRequest();
			updateRequest.index(index);
			updateRequest.type(type);
			updateRequest.id(id);


			try {
				updateRequest.doc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field(fieldName, value)
                        .endObject());
				UpdateResponse updateResponse = client.update(updateRequest).get();
                if(updateResponse.status().getStatus() == RestStatus.OK.getStatus()){
                    return 1;
                }else{
                    logger.error("update failed {}",updateResponse.status().getStatus());
                }
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

	public Integer indexWithId(T t,String id){
		if(null != index && null != type){
			IndexResponse indexResponse = client.prepareIndex(index, type,id).setSource(JSON.toJSONString(t)).get();
			if(indexResponse.getId() != null){
				return 1;
			}
		}
		return 0;
	}

	public Integer bulkIndex(List<T> ts) {
		if(null != index && null != type && null != ts && !ts.isEmpty()){
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for(T t:ts){
				bulkRequest.add(client.prepareIndex(index, type).setSource(JSON.toJSONString(t)));
			}
			BulkResponse bulkResponse = bulkRequest.get();
			if (bulkResponse.hasFailures()) {
				throw new RuntimeException("bulk index failed");
			}
			return ts.size();
		}
		return 0;
	}
	
	public String indexAndReturnId(T t) {
		if(null != index && null != type){
			IndexResponse indexResponse = client.prepareIndex(index, type).setSource(JSON.toJSONString(t)).get();
			return indexResponse.getId();
		}
		return null;
	}

	public T getDocumentById(String id,Class<T> clz) {
		GetResponse getResponse = client.prepareGet(index, type, id).get();
		return JSON.parseObject(getResponse.getSourceAsString(), clz);
		
	}

	public List<T> multiGetDocumentByIds(List<String> id,Class<T> clz) {
		List<T> result = null;
		MultiGetRequestBuilder multiGetRequestBuilder = client.prepareMultiGet().add(index, type, id);
		MultiGetResponse multiGetResponse = multiGetRequestBuilder.get();
		if(null != multiGetResponse){
			result = new ArrayList<T>();
			for (MultiGetItemResponse itemResponse : multiGetResponse) {
			    GetResponse response = itemResponse.getResponse();
			    if (response.isExists()) {                      
			        String json = response.getSourceAsString(); 
			        result.add(JSON.parseObject(json, clz));
			    }
			}
		}
		return result;
	}

	public ElasticSearchConfig index(String index) {
		this.index = index;
		return this;
	}

	public ElasticSearchConfig type(String type) {
		this.type = type;
		return this;
	}

	public String searchByTemplate(String scriptLocation, Map<String, Object> params) {
		SearchResponse sr = new SearchTemplateRequestBuilder(client)
		        .setScript(FileToJsonStrUtils.getJSONStr(scriptLocation))
		        .setScriptType(ScriptType.INLINE)
		        .setScriptParams(params)                  
		        .setRequest(new SearchRequest())
		        .get()                                             
		        .getResponse();   
		if(sr != null){
			return  sr.toString();
		}
		return null;
	}

	public Boolean deleteById(String id) {
		DeleteResponse response = client.prepareDelete(index, type, id).get();
		return response.getResult().getLowercase().equals("deleted");
	}

	@Override
	public Boolean deleteAllDatas(String index) {
		BulkByScrollResponse response =
				DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
						.filter(QueryBuilders.matchAllQuery())
						.source(index)
						.get();
		long deleted = response.getDeleted();
		return deleted > 0 ? true : false;
	}

	public String simpleAggregationByTemplate(String templateName, String fieldName) {
		Map<String, Object> params = new HashMap<>(1);
		params.put("aggsField",fieldName);
		SearchResponse sr = new SearchTemplateRequestBuilder(client)
		        .setScript(FileToJsonStrUtils.getJSONStr(templateName))
		        .setScriptType(ScriptType.INLINE)
		        .setScriptParams(params)                  
		        .setRequest(new SearchRequest())
		        .get()                                             
		        .getResponse();
		return sr.toString();
	}

	@Override
	public List<T> queryAllObjectBySorts(List<String> sortFields,Class<T> clz) {

		List<T> result = new ArrayList<>();
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type).setFrom(0).setSize(20).setQuery(QueryBuilders.matchAllQuery());
		if(!CollectionUtils.isEmpty(sortFields)){
			for(String eachField : sortFields){
				searchRequestBuilder.addSort(eachField, SortOrder.DESC);
			}
		}

		SearchResponse response = searchRequestBuilder.setExplain(true).get();
		if(null != response){
			SearchHits searchHits = response.getHits();
			SearchHit[] hits = searchHits.getHits();
			for (int i = 0;i < hits.length;i++){
				T t = JSON.parseObject(hits[i].getSourceAsString(),clz);
				result.add(t);
			}
		}
		return result;
	}

	@Override
	public String searchSuggester(String scriptLocation, String key) {
		Map<String, Object> params = new HashMap<>(1);
		params.put("key",key);
		SearchResponse sr = new SearchTemplateRequestBuilder(client)
				.setScript(FileToJsonStrUtils.getJSONStr(scriptLocation))
				.setScriptType(ScriptType.INLINE)
				.setScriptParams(params)
				.setRequest(new SearchRequest())
				.get()
				.getResponse();
		return sr.toString();
	}

	@Override
	public Page<T> page(int pageNo, int pageSize, Class<T> clz) {

		Page<T> page = new Page<>();

		List<T> result = new ArrayList<>();

		SearchResponse countResponse = client.prepareSearch(index).setSource(new SearchSourceBuilder().size(0).query(QueryBuilders.matchAllQuery())).get();

		if(countResponse != null){
			long totalCount = countResponse.getHits().getTotalHits();
			if(totalCount > 0){
				SearchResponse response = client.prepareSearch(index)
						.setTypes(type)
						.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
						.setQuery(QueryBuilders.matchAllQuery())                 // Query
						.setFrom((pageNo-1) * pageSize).setSize(pageSize).setExplain(true)
						.get();
				if(null != response){

					SearchHits searchHits = response.getHits();
					SearchHit[] hits = searchHits.getHits();
					for (int i = 0;i < hits.length;i++){
						T t = JSON.parseObject(hits[i].getSourceAsString(),clz);
						result.add(t);
					}
					page.setRoot(result);
					page.setTotal((int)totalCount);
					page.setCurrentPage(pageNo);
					page.setLimit(pageSize);
					return page;
				}
			}
		}
		return new Page<>();
	}

	//	public void scroll() {
//		QueryBuilder qb = matchAllQuery();
//		SearchResponse scrollResp = client.prepareSearch(index)
//		        .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.ASC)
//		        .setScroll(new TimeValue(60000))
//		        .setQuery(qb)
//		        .setSize(1).get();
//		do {
//		    for (SearchHit hit : scrollResp.getHits().getHits()) {
//		    	System.out.println(JSON.parseObject(hit.getSourceAsString(),Phone.class).getId());
//		    }
//		    scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
//		} while(scrollResp.getHits().getHits().length != 0);
//	}


}
