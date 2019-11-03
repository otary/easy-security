package cn.chenzw.security.easy.server.initializer;

import cn.chenzw.security.easy.server.filter.EasySecurityFilter;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * @author chenzw
 */
public class EasySecurityInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 认证过滤器
        FilterRegistration.Dynamic easySecurityFilter = servletContext.addFilter("_easySecurityFilter", EasySecurityFilter.class);
        easySecurityFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
    }
}
