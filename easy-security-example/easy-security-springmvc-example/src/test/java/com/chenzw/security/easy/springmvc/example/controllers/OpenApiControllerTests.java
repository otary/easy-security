package com.chenzw.security.easy.springmvc.example.controllers;

import cn.chenzw.security.easy.client.EasySecurityClient;
import cn.chenzw.security.easy.server.filter.EasySecurityFilter;
import com.chenzw.security.easy.springmvc.example.config.AppConfig;
import com.chenzw.security.easy.springmvc.example.config.EasySecurityConfig;
import com.chenzw.security.easy.springmvc.example.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class, EasySecurityConfig.class})
@WebAppConfiguration
public class OpenApiControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(new EasySecurityFilter())  // 添加过滤器
                .build();
    }

    @Test
    public void testHelloWithNoToken() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/openapi/hello"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(equalTo("{\"code\": 401, \"message\":\"AccessToken is null!\"}")));
    }

    @Test
    public void testHelloWidthToken() throws Exception {
        String accessToken = EasySecurityClient.generateAccessToken("b51d5af6c801ff4bfd31a4a154f35d35", "my", "");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/openapi/hello").header("accessToken", accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello, zhangsan")));
    }


    /**
     * 非法token
     *
     * @throws Exception
     */
    @Test
    public void testHelloWidthInvalidToken() throws Exception {
        String accessToken = EasySecurityClient.generateAccessToken("b51d5af6c801ff4bfd31a4a154f35d3", "my", "");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/openapi/hello").header("accessToken", accessToken))
                .andExpect(status().isForbidden())
                .andExpect(content().string(equalTo("{\"code\": 403, \"message\":\"Token is invaild!\"}")));
    }

}
