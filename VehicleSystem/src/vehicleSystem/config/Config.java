package vehicleSystem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("vehicleSystem")
@PropertySource("classpath:application.properties")
public class Config {
}
