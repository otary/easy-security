# easy-security

简易认证解决方案

![](https://img.shields.io/badge/springframework-4.3.15.RELEASE-brightgreen)
![](https://img.shields.io/badge/jdk-1.8%2B-brightgreen)
![](https://img.shields.io/badge/commons--lang3-3.7-brightgreen)
![](https://img.shields.io/badge/commons--codec-1.12-brightgreen)
![](https://img.shields.io/badge/toolkit-1.0.1-brightgreen)
[![Build Status](https://travis-ci.org/otary/easy-security.svg?branch=master)](https://travis-ci.org/otary/easy-security)

## 文档

[中文文档](https://otary.github.io/easy-security/)

## 模块

- [easy-security-core](./easy-security-core): 核心包
- [easy-security-server](./easy-security-server): 认证服务端
  - [使用教程](./easy-security-server/README.md)
- [easy-security-client](./easy-security-client): 客户端工具类
  - [使用教程](./easy-security-client/README.md)
- [easy-security-keygen](./easy-security-keygen): 密钥生成器
  - [使用教程](./easy-security-keygen/README.md)
- [easy-security-example](./easy-security-example): 示例
  - [easy-security-springmvc-example](./easy-security-example/easy-security-springmvc-example): springmvc项目集成示例
  - [easy-security-springboot-example](./easy-security-example/easy-security-springboot-example): springboot项目集成示例

## 响应状态码

状态码 | 消息 | 说明 | 
---|---|---
401 | AccessToken is null | 请求header中未携带认证token
401 | sysId is null |  请求header中未携带sysId参数
401 | Token timestamp is null | token中timestamp为空
403 | Token is invaild | token解析出错（sysId和密钥不匹配）
403 | Token timestampe is invalid | token中timestamp解析出错
403 | Token expired with timestamp [] | token过期（检查服务器时间）
403| You has no permission to access this api | 没有权限访问此API
403 | This api internal access only | 该API未开放外部访问


[](zh-cn/easy-security-server/README.md ':include')

[](zh-cn/easy-security-client/README.md ':include')

[](zh-cn/easy-security-keygen/README.md ':include')