package com.hs.datatrans.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class DBConnectionConfig {
    private static Logger log = Logger.getLogger(DBConnectionConfig.class);
    private static DruidDataSource dataSource;

    static {
        InputStreamReader reader = null;
        try {
            Properties pro = new Properties();
            reader= new InputStreamReader(DBConnectionConfig.class.getClassLoader().getResourceAsStream("db.properties"), "UTF-8");
            pro.load(reader);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException("数据库配置加载失败");
        }finally {
            if(null!=reader ){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private DBConnectionConfig() {

    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }

}
