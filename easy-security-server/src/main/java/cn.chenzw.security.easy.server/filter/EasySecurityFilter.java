package cn.chenzw.security.easy.server.filter;

import cn.chenzw.security.easy.core.constants.EasySecurityConstants;
import cn.chenzw.security.easy.core.exception.EasySecurityException;
import cn.chenzw.security.easy.server.handler.HandlerMethodEasySecurityHandler;
import cn.chenzw.security.easy.server.handler.EasySecurityHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;


/**
 * 认证拦截器
 *
 * @author chenzw
 */
public class EasySecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(EasySecurityFilter.class);

    private List<EasySecurityHandler> easySecurityHandlers;

    public EasySecurityFilter() {
        logger.debug("init securityFilter...");

        this.easySecurityHandlers = new ArrayList<>();
        this.easySecurityHandlers.add(new HandlerMethodEasySecurityHandler());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (StringUtils.isNotEmpty(EasySecurityConstants.getOpenApiMapping())) {
            if (!StringUtils.startsWithAny(request.getRequestURI(), StringUtils.split(EasySecurityConstants.getOpenApiMapping(), ","))) {
                filterChain.doFilter(request, response);
            }
        }

        logger.debug("security filter url [{}].", request.getRequestURI());

        try {
            for (EasySecurityHandler easySecurityHandler : easySecurityHandlers) {
                if (easySecurityHandler.matches(request, response)) {
                    logger.debug("security filter url [{}] with handler [{}]", request.getRequestURI(), easySecurityHandler);
                    easySecurityHandler.process(request, response);
                }
            }
            filterChain.doFilter(request, response);
        } catch (EasySecurityException e) {
            logger.error("security filter url [{}] failure.", request.getRequestURI());

            handleResponse(response, e.getHttpCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("security filter url [{}] failure.", request.getRequestURI(), e);

            handleResponse(response, 500, e.getMessage());
        }
    }


    private void handleResponse(HttpServletResponse response, int code, String message) throws IOException {
        response.reset();
        response.setCharacterEncoding(EasySecurityConstants.getENCODING());
        response.setContentType("application/json;charset=" + EasySecurityConstants.getENCODING());
        response.setStatus(code);
        response.getWriter().write("{\"code\": " + code + ", \"message\":\"" + message + "\"}");
    }

}
