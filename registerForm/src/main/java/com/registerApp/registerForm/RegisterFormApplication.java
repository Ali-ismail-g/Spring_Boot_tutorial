package com.registerApp.registerForm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RegisterFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterFormApplication.class, args);
	}

}
