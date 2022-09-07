package com.walle.test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 123
 * @create 2022/9/6 23:30
 */
public class Test2 {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // 指向字节码根路径下的文件
        InputStream inputStream = Test2.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String property = properties.getProperty("driver");
        System.out.println(property);
    }
}
