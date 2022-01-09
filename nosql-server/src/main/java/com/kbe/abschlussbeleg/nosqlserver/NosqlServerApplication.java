package com.kbe.abschlussbeleg.nosqlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NosqlServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NosqlServerApplication.class, args);
	}

}
