package com.geekcattle.task;

import com.geekcattle.aijia.domain.AccountAjfInfo;
import com.geekcattle.aijia.domain.BuildingInfo;
import com.geekcattle.aijia.domain.CompanyInfo;
import com.geekcattle.aijia.domain.UserInfo;
import com.geekcattle.aijia.mapper.AccountInfoMapper;
import com.geekcattle.elasticsearch.support.impl.ElasticSearchApiImpl;
import com.geekcattle.task.ajf.AjbComputeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author lgl
 * @time 2017/11/16 10:47
 * @desc
 */
@Component
public class AccountAjfTask extends AbstractBaseTask implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(AccountAjfTask.class);

    private final static ExecutorService executor = Executors.newFixedThreadPool(5);


    @Resource
    private AccountInfoMapper accountInfoMapper;


    private static Map<Integer,String> buildingInfosMap;

    private static Map<Integer,String> companyInfosMaps;

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
    public void prepare() {

        List<BuildingInfo> buildingInfos = accountInfoMapper.queryAllBuildingInfo();

        if(!CollectionUtils.isEmpty(buildingInfos)){
            buildingInfosMap = buildingInfos.stream().collect(Collectors.toMap(BuildingInfo::getId, BuildingInfo::getName));
        }

        List<CompanyInfo> companyInfos = accountInfoMapper.queryAllCompanyInfo();

        if(!CollectionUtils.isEmpty(companyInfos)){
            companyInfosMaps = companyInfos.stream().collect(Collectors.toMap(CompanyInfo::getCompanyId, CompanyInfo::getName));
        }


    }

    @Override
    public void compute() {

        prepare();

        List<UserInfo> userInfos = accountInfoMapper.selectHasAjbUserInfo();

        if(null != userInfos && !CollectionUtils.isEmpty(userInfos)){

            logger.info("userInfos size is {}",userInfos.size());

            for(UserInfo userInfo : userInfos){

                try{
                    AjbComputeUnit ajbComputeUnit = new AjbComputeUnit(userInfo,this);
//                    单线程测试
//                    ajbComputeUnit.run();
                    executor.submit(ajbComputeUnit);
                }catch (Exception e){
                    //igore
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        elasticSearchImpl = new ElasticSearchApiImpl<AccountAjfInfo>(host, port, nodeName);
        elasticSearchImpl.index(index).type(type);

    }

    public AccountInfoMapper getAccountInfoMapper() {
        return accountInfoMapper;
    }

    public void setAccountInfoMapper(AccountInfoMapper accountInfoMapper) {
        this.accountInfoMapper = accountInfoMapper;
    }

    public static Map<Integer, String> getBuildingInfosMap() {
        return buildingInfosMap;
    }

    public static void setBuildingInfosMap(Map<Integer, String> buildingInfosMap) {
        AccountAjfTask.buildingInfosMap = buildingInfosMap;
    }

    public static Map<Integer, String> getCompanyInfosMaps() {
        return companyInfosMaps;
    }

    public static void setCompanyInfosMaps(Map<Integer, String> companyInfosMaps) {
        AccountAjfTask.companyInfosMaps = companyInfosMaps;
    }

    public ElasticSearchApiImpl<AccountAjfInfo> getElasticSearchImpl() {
        return elasticSearchImpl;
    }

    public void setElasticSearchImpl(ElasticSearchApiImpl<AccountAjfInfo> elasticSearchImpl) {
        this.elasticSearchImpl = elasticSearchImpl;
    }
}
