package com.medisite.medicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.medisite.medicos")
@EnableJpaRepositories(basePackages = "com.medisite.medicos.repository")
public class MedicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicosApplication.class, args);
	}
}
