package com.zjw.light.helper;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zjw.light.annotation.Controller;
import com.zjw.light.annotation.RequestMapping;
import com.zjw.light.helper.scanner.ClassScanner;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/31
 */
public class ControllerHelper {
    public static Map<String, Handler> getControllerMap() {
        return controllerMap;
    }

    private static final Map<String, Handler> controllerMap = new LinkedHashMap<String, Handler>();
    static {
        String packageName = ConfigHelper.getString("base_package");
        ClassScanner classScanner = InstanceFactory.getClassScanner();
        List<Class<?>> base_package = classScanner.getClassListByAnnotation(packageName, Controller.class);
        System.out.println("");
        if(base_package.size()>0){
            for (Class<?> aClass : base_package) {
                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if(declaredMethod.isAnnotationPresent(RequestMapping.class)){
                        String value = declaredMethod.getAnnotation(RequestMapping.class).value();
                        controllerMap.put(value,new Handler(aClass,declaredMethod));
                    }
                }
            }
        }
    }

}
