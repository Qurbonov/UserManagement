package mf.um;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class UserManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(RootConfig.class, args);
    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        registrationBean.setFilter(characterEncodingFilter);
//        return registrationBean;
//    }
}
