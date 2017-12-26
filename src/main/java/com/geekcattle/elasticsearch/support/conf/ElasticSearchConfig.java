package com.geekcattle.elasticsearch.support.conf;

public interface ElasticSearchConfig {
	
	public ElasticSearchConfig index(String index);
	
	public ElasticSearchConfig type(String type);

}
