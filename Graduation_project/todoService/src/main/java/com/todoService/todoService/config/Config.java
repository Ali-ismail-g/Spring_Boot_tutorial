package com.todoService.todoService.config;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterRegistration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class Config implements WebMvcConfigurer {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
    public final String authServiceUrl="http://localhost:8080/rest/auth/details";
//    @Bean
//    public FilterRegistrationBean getPeticionFilter(){
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new RequestFilter());
//        filterRegistration.addUrlPatterns("/*");
//        filterRegistration.setName("requestFilter");
//        return filterRegistration;
//    }
//    @PostConstruct
//    public void addInterceptors(){
//        List<ClientHttpRequestInterceptor> interceptorList = restTemplate().getInterceptors();
//        interceptorList.add(new RestTemplateInterceptor());
//        restTemplate().setInterceptors(interceptorList);
//    }
}
