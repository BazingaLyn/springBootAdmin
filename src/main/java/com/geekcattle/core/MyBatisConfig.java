/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.core;

import com.geekcattle.database.DatabaseType;
import com.geekcattle.database.DynamicDataSource;
import com.github.pagehelper.PageHelper;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * MyBatis基础配置
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Bean(name = "geekcattle.dataSource")
    @ConfigurationProperties(prefix = "geekcattle.spring.datasource")
    public DataSource geekcattleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "account.dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mybatis")
    public DataSource DataSource() {
        String user = "aijia";
        String password = "QX9CA&1cYB9*eWwS";
        String host = "121.196.205.228";
        int port=2525;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            String rhost = "10.11.0.1";
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            int assinged_port=session.setPortForwardingL("127.0.0.1",3310, "10.11.0.1", 3306);
        }
        catch(Exception e){System.err.print(e);}
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("geekcattle.dataSource") DataSource geekcattleDataSource,
                                          @Qualifier("account.dataSource") DataSource accountDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.account, accountDataSource);
        targetDataSources.put(DatabaseType.geekcattle, geekcattleDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(geekcattleDataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource ds) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setTypeAliasesPackage("com.geekcattle.model");

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(geekcattleDataSource());
    }
}
