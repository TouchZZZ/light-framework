package com.zjw.light;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zjw.light.helper.ControllerHelper;
import com.zjw.light.helper.Handler;
import com.zjw.light.util.ClassUtil;


/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/22
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("转发器初始化");
        super.init();
    }

    private Map<Class<?>,Object> beanList = new HashMap<Class<?>,Object>();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("转发");
        // 设置请求编码方式
        request.setCharacterEncoding("UTF-8");
        // super.service(req, resp);
        String servletPath = request.getServletPath();
        String pathInfo=request.getPathInfo();
        String path = pathInfo==null?servletPath:servletPath+pathInfo;
        ServletInputStream inputStream = request.getInputStream();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        String json =result.toString("UTF-8");

        if (path != null) {
            Map<String, Handler> controllerMap = ControllerHelper.getControllerMap();
            if(controllerMap.containsKey(path)){
                Handler handler = controllerMap.get(path);
                if(beanList.containsKey(handler.getActionClass())){
                    Object o = beanList.get(handler.getActionClass());
                    try {
                        handler.getActionMethod().setAccessible(true);
                        Class<?>[] parameterTypes = handler.getActionMethod().getParameterTypes();
                        if(parameterTypes.length>0){
                            Object o1 = JSON.parseObject(json, parameterTypes[0]);
                            handler.getActionMethod().invoke(o,o1);
                        }else{
                            handler.getActionMethod().invoke(o,null);
                        }



                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    // Class<?> aClass = ClassUtil.newInstance(handler.getActionClass());
                    try {
                        Object o = handler.getActionClass().newInstance();
                        beanList.put(handler.getActionClass(),o);
                        Class<?>[] parameterTypes = handler.getActionMethod().getParameterTypes();
                        if(parameterTypes.length>0){
                            Object o1 = JSON.parseObject(json, parameterTypes[0]);
                            handler.getActionMethod().invoke(o,o1);
                        }else{
                            handler.getActionMethod().invoke(o,null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // response.sendRedirect(request.getContextPath() + path);

        return;
    }
}
