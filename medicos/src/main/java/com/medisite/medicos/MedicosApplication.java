package com.medisite.medicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.medisite.medicos")
@EntityScan(basePackages = "com.medisite.medicos.model")
@EnableJpaRepositories(basePackages = "com.medisite.medicos.repository")
public class MedicosApplication {
	public static void main(String[] args) {
		SpringApplication.run(MedicosApplication.class, args);
	}
}
