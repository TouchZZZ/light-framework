package com.zjw.demo.controller;

import com.zjw.light.annotation.Controller;
import com.zjw.light.annotation.RequestMapping;

/**
 * @author zhangjinwei
 * @version v1.0.0
 * @Project test
 * @Description
 * @date 2018/5/31
 */
@Controller
public class TestController {



    @RequestMapping(value="/a")
    public void a(TestVo vo){
        System.out.println(vo.getStatus());
    }

    @RequestMapping(value="/b")
    public void b(){
        System.out.println("b");
    }
}
