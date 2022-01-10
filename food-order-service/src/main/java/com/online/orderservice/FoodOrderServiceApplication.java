package com.online.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FoodOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderServiceApplication.class, args);
	}

}
