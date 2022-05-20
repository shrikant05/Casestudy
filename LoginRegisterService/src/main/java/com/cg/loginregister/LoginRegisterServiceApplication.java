package com.cg.loginregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "Login & Registeration API", version = "1.0", description = "Login in for Using Railway Reservation Service API"))
public class LoginRegisterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterServiceApplication.class, args);
	}

}
