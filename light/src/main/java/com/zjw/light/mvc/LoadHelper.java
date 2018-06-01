package com.zjw.light.mvc;

import com.zjw.light.helper.ControllerHelper;
import com.zjw.light.util.ClassUtil;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/30
 */
public class LoadHelper {

    public static void init() {
        Class<?>[] classList = {
           ControllerHelper.class
        };
        for (Class<?> aClass : classList) {
            ClassUtil.loadClass(aClass.getName());
        }
        

    }
}
