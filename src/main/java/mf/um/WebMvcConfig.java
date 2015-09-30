package mf.um;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qurbonov on 8/28/2015.
 */
@Configuration
@ComponentScan
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private
    @Autowired
    RequestMappingHandlerAdapter adapter;

    @PostConstruct
    public void prioritizeCustomArgumentMethodHandlers() {
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>(adapter.getArgumentResolvers());
        List<HandlerMethodArgumentResolver> customResolvers = adapter.getCustomArgumentResolvers();
        argumentResolvers.removeAll(customResolvers);
        argumentResolvers.addAll(0, customResolvers);
        adapter.setArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PrincipalArgumentResolver());
    }

    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LoginFilter());
        return bean;
    }
}
