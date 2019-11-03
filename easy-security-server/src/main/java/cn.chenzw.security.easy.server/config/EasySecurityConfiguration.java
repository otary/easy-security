package cn.chenzw.security.easy.server.config;

import cn.chenzw.security.easy.core.constants.EasySecurityConstants;
import cn.chenzw.security.easy.server.filter.EasySecurityFilter;
import cn.chenzw.toolkit.spring.annotation.EnableToolkit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration("_easySecurityConfiguration")
@Import({EasySecurityConstants.class})
@ComponentScan(basePackages = EasySecurityConstants.APP_BASE_PACKAGE)
@EnableToolkit
public class EasySecurityConfiguration {

    @Bean
    public EasySecurityFilter securityFilter() {
        return new EasySecurityFilter();
    }

}
