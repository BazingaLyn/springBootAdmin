package com.geekcattle.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource）
 *
 * @author liguolin
 * @create 2017-12-26 16:13
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

      protected Object determineCurrentLookupKey() {
          return DatabaseContextHolder.getDatabaseType();
      }
 }