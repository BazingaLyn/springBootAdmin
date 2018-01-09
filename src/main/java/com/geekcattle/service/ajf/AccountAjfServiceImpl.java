package com.geekcattle.service.ajf;

import com.geekcattle.aijia.domain.AccountAjfInfo;
import com.geekcattle.elasticsearch.support.impl.ElasticSearchApiImpl;
import com.geekcattle.model.vo.SuggestResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户艾积分查询详细
 *
 * @author liguolin
 * @create 2018-01-04 17:33
 **/
@Service
public class AccountAjfServiceImpl implements AccountAjfService,InitializingBean {



    @Value("${es.index.bazinga}")
    private String index;
    @Value("${es.type.ajf}")
    private String type;
    @Value("${es.host}")
    private String host;
    @Value("${es.port}")
    private Integer port;
    @Value("${es.elasticsearchNodeName}")
    private String nodeName;

    public ElasticSearchApiImpl<AccountAjfInfo> elasticSearchImpl;


    @Override
    public PageInfo<AccountAjfInfo> pageAjfInfo(String key, Integer offset, Integer limit) {

        return elasticSearchImpl.page(offset,limit,AccountAjfInfo.class);

    }

    @Override
    public List<SuggestResult> suggestList(String keyword) {

        List<SuggestResult> suggestResults = elasticSearchImpl.suggestList(keyword);

        return suggestResults;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        elasticSearchImpl = new ElasticSearchApiImpl<AccountAjfInfo>(host, port, nodeName);
        elasticSearchImpl.index(index).type(type);
    }
}
