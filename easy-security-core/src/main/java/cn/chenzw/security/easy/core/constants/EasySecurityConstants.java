package cn.chenzw.security.easy.core.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:" + EasySecurityConstants.CONFIG_FILE_NAME}, ignoreResourceNotFound = true)
public class EasySecurityConstants {

    public static final String CONFIG_FILE_NAME = "security.properties";

    public static final String KEY_TIMESTAMP_FORMAT = "yyyyMMddHHmmss";

    public static final String DEFAULT_TOKEN_HEADER_NAME = "accessToken";

    public static final String APP_BASE_PACKAGE = "cn.chenzw.security.easy.server";

    /**
     * 密钥
     */
    public static String PRIVATE_KEY;

    /**
     * 时效性（默认:30分钟）
     */
    public static int LIMIT_MILLI_SECOND;

    /**
     * 拦截的uri地址
     */
    public static String OPEN_API_MAPPING;

    /**
     * 编码
     */
    public static String ENCODING;


    @Value("${easy.security.private-key:123456}")
    public void setPrivateKey(String privateKey) {
        PRIVATE_KEY = privateKey;
    }

    @Value("${easy.security.limit-milli-second:1800000}")
    public void setLimitMilliSecond(int limitMilliSecond) {
        LIMIT_MILLI_SECOND = limitMilliSecond;
    }

    @Value("${easy.security.open-api-mapping:/openapi}")
    public void setOpenApiMapping(String openApiMapping) {
        OPEN_API_MAPPING = openApiMapping;
    }

    @Value("${easy.security.encoding:utf-8}")
    public void setENCODING(String ENCODING) {
        EasySecurityConstants.ENCODING = ENCODING;
    }

    public static String getPrivateKey() {
        return PRIVATE_KEY;
    }

    public static String getOpenApiMapping() {
        return OPEN_API_MAPPING;
    }

    public static String getENCODING() {
        return ENCODING;
    }


}
