package com.chenzw.security.easy.springmvc.example.controllers;

import cn.chenzw.security.easy.server.ext.OpenApiSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openapi")
public class OpenApiController {

    @GetMapping("/hello")
    @OpenApiSecurity(allowSysId = "*")
    public String hello() {
        return "hello, zhangsan";
    }


}
