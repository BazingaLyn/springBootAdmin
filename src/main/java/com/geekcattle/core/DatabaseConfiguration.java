package com.geekcattle.core;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author lgl
 * @time 2017/11/14 9:58
 * @desc
 */
@Configuration
@MapperScan(basePackages = "com.geekcattle.aijia.mapper", sqlSessionTemplateRef  = "accountSqlSessionTemplate")
public class DatabaseConfiguration {

    private final Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class);


    @Bean(name = "accountSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("account.dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:aijiamapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "accountTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("account.dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "accountSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
