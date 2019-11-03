package cn.chenzw.security.easy.server.handler;

import cn.chenzw.security.easy.core.domain.entity.AccessTokenEntity;
import cn.chenzw.security.easy.core.exception.EasySecurityException;
import cn.chenzw.security.easy.core.util.SecurityUtils;
import cn.chenzw.security.easy.server.ext.OpenApiSecurity;
import cn.chenzw.toolkit.spring.util.SpringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author chenzw
 */
public class HandlerMethodEasySecurityHandler extends AbstractEasySecurityHandler {

    ThreadLocal<HandlerMethod> handlerMethodTL = new ThreadLocal<>();

    @Override
    public boolean matches(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerMethod handlerMethod = SpringUtils.getHanlerMethod(request);
        if (handlerMethod != null) {
            handlerMethodTL.set(handlerMethod);
            return true;
        }
        return false;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HandlerMethod handlerMethod = handlerMethodTL.get();
        if (handlerMethod == null) {
            throw new EasySecurityException(500, "HandlerMethod is null");
        }

        if (handlerMethod.hasMethodAnnotation(OpenApiSecurity.class)) {
            OpenApiSecurity openApiSecurity = handlerMethod.getMethodAnnotation(OpenApiSecurity.class);

            AccessTokenEntity accessTokenEntity = SecurityUtils.parseAccessToken(request);

            if (ArrayUtils.contains(openApiSecurity.allowSysId(), "*")) {
                // 允许所有sysId访问

            } else if (openApiSecurity.allowSysId().length > 0) {
                // 允许部分sysId访问
                if (!ArrayUtils.contains(openApiSecurity.allowSysId(), accessTokenEntity.getSysId())) {
                    throw new EasySecurityException(403, "You has no permission to access this api.");
                }
            }

            // 时间戳校验
            String timestamp = accessTokenEntity.getPlainToken().getTimestamp();

            if (!checkTokenExpired(timestamp)) {
                // token过期
                throw new EasySecurityException(403, "Token expired with timestamp [" + timestamp + "]");
            }

            // @TODO 用户名 + extData + request参数 校验扩展

        } else {
            throw new EasySecurityException(403, "This api internal access only.");
        }
    }


}
