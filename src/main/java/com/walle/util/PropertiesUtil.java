package com.walle.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 123
 * @create 2022/9/8 0:06
 */
public class PropertiesUtil {
    private Properties properties;

    public PropertiesUtil(String path) {
        properties = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
