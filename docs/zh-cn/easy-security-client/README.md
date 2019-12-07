# easy-security-client

客户端工具类

## Usage

### 添加依赖

- **maven方式**
 
 ``` xml
<dependency>
    <groupId>cn.chenzw.security</groupId>
    <artifactId>easy-security-client</artifactId>
    <version>1.0.0</version>
</dependency>

```

- **gradle方式**

```
compile group: 'cn.chenzw.security', name: 'easy-security-client', version: '1.0.0'
```


### 调用方法

``` java
/**
  * 生成accessToken头部
  * @param privateKey 参数1：密钥（由服务端提供）
  * @param sysId 参数2：系统标识符
  * @param userName 参数3：访问的用户名（可为空）
  * @return
  * @throws UnsupportedEncodingException
  **/
String accessToken = EasySecurityClient.generateAccessToken("b51d5af6c801ff4bfd31a4a154f35d35", "crm", "admin");
response.setHeader("accessToken", accessToken);


// 或直接设置头部
EasySecurityClient.setHeaderToken(response, "b51d5af6c801ff4bfd31a4a154f35d35", "crm", "admin");

```

