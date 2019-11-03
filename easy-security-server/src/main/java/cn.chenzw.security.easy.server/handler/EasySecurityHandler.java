package cn.chenzw.security.easy.server.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 认证处理器
 *
 * @author chenzw
 */
public interface EasySecurityHandler {

    boolean matches(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 检查token是否过期
     *
     * @param timestamp
     * @return
     * @throws Exception
     */
    boolean checkTokenExpired(final String timestamp) throws Exception;

    void process(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
