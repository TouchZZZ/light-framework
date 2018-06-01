package com.zjw.light.helper;

import java.util.Properties;

import com.zjw.light.util.PropsUtil;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/31
 */
public class ConfigHelper {
    private static Properties props = PropsUtil.loadProps("smart.properties");


    public static String getString(String key) {
        return PropsUtil.getString(props,key);
    }
}
