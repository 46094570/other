package com.hs.datatrans.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class ReadConfig {

    private Properties config = new Properties();


    public ReadConfig() {
        Reader is = null;
        try {
            is = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("config.properties"), "UTF-8");
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

    public Properties getConfig() {
        return config;
    }

}
