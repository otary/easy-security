# easy-security-server

认证服务端

## Usage


### 添加依赖
 
- maven 方式
 
 ```
<dependency>
    <groupId>cn.chenzw.security</groupId>
    <artifactId>easy-security-server</artifactId>
    <version>1.0.0</version>
</dependency>
```

- gradle 方式

```
compile group: 'cn.chenzw.security', name: 'easy-security-server', version: '1.0.0'

```

### 开启服务端认证

- 使用`@EnableEasySecurity`开启认证服务

```
@EnableEasySecurity
@Configuration
public class EasySecurityConfig {

}
```

### 配置相关参数

- 可在项目`resources`下新建`security.properties` 或 直接在`application.properties`中添加单点配置参数

```
## 指定服务端私钥（必须）
easy.security.private-key=123456

## 指定token的时效（默认:30分钟）（可选）
easy.security.limit-milli-second=1800000

## 指定对哪些链接进行认证（多个地址间使用逗号间隔）（可选）
easy.security.open-api-mapping=/openapi,/rest

## 指定响应编码（可选）
easy.security.encoding=utf-8
```

## 使用@OpenApiSecurity注解标注需要认证的api

```
@RestController
@RequestMapping("/openapi")
public class OpenApiController {

    @GetMapping("/hello")
    @OpenApiSecurity(allowSysId = "*")   // 表示开放对外访问，允许任意sysId
    public String hello() {
        return "hello, zhangsan";
    }

}
```

## 附录

##### @OpenApiSecurity注解

属性
- allowSysId: 用户指定允许访问的sysId（多个sysId之间使用逗号间隔）, *号表示允许所有sysId访问
