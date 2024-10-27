package com.huan.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping()
    public String test(){
        return "ok";
    }

    @GetMapping("test")
    public String test2(){
        return "ok2";
    }
}
