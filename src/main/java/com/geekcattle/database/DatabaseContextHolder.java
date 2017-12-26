package com.geekcattle.database;

/**
 * 保存一个线程安全的DatabaseType容器
 *
 * @author liguolin
 * @create 2017-12-26 16:12
 **/
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}
