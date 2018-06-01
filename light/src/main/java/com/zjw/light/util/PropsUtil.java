package com.zjw.light.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/31
 */
public class PropsUtil {
    public static Properties loadProps(String path)  {
        Properties props = new Properties();
        InputStream is = null;
        is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static String getString(Properties props , String key){
        if(props.containsKey(key)){
            return props.getProperty(key);
        }
        return "";
    }
}
