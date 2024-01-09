package main.com.myApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@RefreshScope
@EnableWebMvc
@ComponentScan("main.com.myApp")
@PropertySource("classpath:application.properties")
//@PropertySource({"classpath:application.properties", "classpath:pom.xml"})
public class webConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
}
