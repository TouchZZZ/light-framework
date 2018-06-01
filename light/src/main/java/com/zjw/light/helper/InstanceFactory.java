package com.zjw.light.helper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zjw.light.helper.scanner.ClassScanner;
import com.zjw.light.helper.scanner.DefaultClassScanner;
import com.zjw.light.util.ClassUtil;
import com.zjw.light.util.StringUtil;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/31
 */
public class InstanceFactory {
    /**
     * 用于缓存对应的实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();
    private static final String CLASS_SCANNER ="class_scanner" ;

    /**
     * 获取 ClassScanner
     */
    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    private static <T> T getInstance(String classScanner, Class<T> defaultClass) {
        //看缓存中是否存在想要的对象
        if(cache.containsKey(classScanner)){
            return  (T)cache.get(classScanner);
        }
        //缓存中没有，则需要先看看配置文件中是否有配置该对象的实现类
        String classPath = ConfigHelper.getString(classScanner);
        // 若实现类配置不存在，则使用默认实现类
        if (StringUtil.isEmpty(classPath)) {
            classPath = defaultClass.getName();
        }
        //通过class路径实例化对象
        T instance = ClassUtil.newInstance(classPath);
        if(null!=instance){
            cache.put(classScanner,instance);
        }
        return instance;
    }

}
