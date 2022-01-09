package com.kbe.abschlussbeleg.cloudgetaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGetawayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGetawayApplication.class, args);
	}

}
