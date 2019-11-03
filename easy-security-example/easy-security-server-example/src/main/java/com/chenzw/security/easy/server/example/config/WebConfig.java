package com.chenzw.security.easy.server.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {"com.chenzw.security.easy.server.example"})
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


}
