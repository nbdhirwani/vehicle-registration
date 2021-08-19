package com.example.rego;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class VehicleRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleRegistrationApplication.class, args);
	}

}
