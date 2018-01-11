package com.geekcattle.elasticsearch.support.api;


import com.geekcattle.aijia.domain.AccountAjfInfo;
import com.geekcattle.model.vo.SuggestResult;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ElasticSearchApi<T> {
	
	public Integer index(T t);

	public Integer update(String fieldName, String value, String id);

	public Integer bulkIndex(List<T> ts);

	public Integer indexWithId(T t, String id);

	public String indexAndReturnId(T t);

	public T getDocumentById(String id, Class<T> clz);

	public List<T> multiGetDocumentByIds(List<String> id, Class<T> clz);

	public String searchByTemplate(String scriptLocation, Map<String, Object> params);

	public Boolean deleteById(String id);

	public Boolean deleteAllDatas(String index);

	public String simpleAggregationByTemplate(String templateName, String field);

    public List<T> queryAllObjectBySorts(List<String> sortFields, Class<T> clz);

	String searchSuggester(String scriptLocation, String key);

	public PageInfo<T> page(int from, int pageSize, Class<T> clz);

    SuggestResult suggestList(String keyword);
}
