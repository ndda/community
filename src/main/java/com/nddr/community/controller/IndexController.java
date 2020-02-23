package com.nddr.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/") //浏览器url地址传入到此注解，调用下列方法；
    public String index(){
        return "index"; //把index.html文件返回给浏览器
    }
}
