package com.nddr.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/hello") //路径
    public String hello(String name, Model model) {//model是实例化类名，用它调用Model的方法
        model.addAttribute("name",name); //双引号内name为传递符号
        return "hello";
    }
    @GetMapping("/world") //说明去哪个地方找world.html
    public String word(String xx,Model jj){   //方法名没关系，xx、jj均为创建变量,xx为？后的输入
        jj.addAttribute("qq",xx);
        return "world";//把值传递给world
    }

//    public static void main(String[] args) {
//        HelloController hc= new HelloController();
//        hc.hello(hello.html);
//        System.out.println("xx");
//    }
}
