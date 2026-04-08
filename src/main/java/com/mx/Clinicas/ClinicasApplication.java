package com.mx.Clinicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClinicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicasApplication.class, args);
	}

}
