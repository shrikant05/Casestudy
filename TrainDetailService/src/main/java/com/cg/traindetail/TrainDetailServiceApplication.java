package com.cg.traindetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title = "TrainDetail API", version = "1.0", description = "All Train Information"))
public class TrainDetailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainDetailServiceApplication.class, args);
	}

}
