package com.hs.datatrans.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

/**
 * 读取Excel 配置文件
 */
public class BasicConfig {

    private static Properties config = new Properties();

    static {
        Reader is = null;
        try {
            is = new InputStreamReader(BasicConfig.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8");
            config.load(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("config.propertis异常\r\n");
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private BasicConfig() {

    }

    public static Properties getConfig() {
        return config;
    }

/*    public static BasicConfig getInstance(){
        return ReadExcelConfigInstance.INSTANCE;
    }

    private static class ReadExcelConfigInstance{
        private static final BasicConfig INSTANCE = new BasicConfig();
    }*/
}
