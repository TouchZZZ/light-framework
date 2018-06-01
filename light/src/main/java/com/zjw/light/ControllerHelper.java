package com.zjw.light;


import com.zjw.light.annotation.Controller;
import com.zjw.light.annotation.RequestMapping;
import com.zjw.light.annotation.RequestMethod;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/23
 */
@Controller
public class ControllerHelper {

    @RequestMapping(value="", method = RequestMethod.POST)
    public void test(){
        System.out.println("test");
    }
}
