# easy-security

简易认证解决方案


## 模块

- [easy-security-core](./easy-security-core): 核心包
- [easy-security-server](./easy-security-server): 认证服务端
  - [使用教程](./easy-security-server/README.md)
- [easy-security-client](./easy-security-client): 客户端工具类
- [easy-security-keygen](./easy-security-keygen): 密钥生成器
- [easy-security-example](./easy-security-example): 示例

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

