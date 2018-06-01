package com.zjw.light.mvc;

import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.zjw.light.annotation.Controller;
import com.zjw.light.helper.ConfigHelper;
import com.zjw.light.helper.InstanceFactory;
import com.zjw.light.helper.scanner.ClassScanner;
import com.zjw.light.util.PropsUtil;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/22
 */
@WebListener
public class ContainerListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //实现容器监听接口，在Servlet容器初始化时候调用该方法，获取上下文
        ServletContext servletContext = sce.getServletContext();
        //开始初始化light容器，加载各种需要的对象
        LoadHelper.init();

        // Properties properties = PropsUtil.loadProps("smart.properties");
        // String class_scanner = PropsUtil.getString(properties, "class_scanner");
        // String packageName = ConfigHelper.getString("base_package");
        // ClassScanner classScanner = InstanceFactory.getClassScanner();
        // List<Class<?>> base_package = classScanner.getClassListByAnnotation(packageName, Controller.class);

        System.out.println("容器初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("容器被销毁");
    }
}
