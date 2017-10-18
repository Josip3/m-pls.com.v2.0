package com.mpls.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mpls.v2.repository")
public class PersistenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersistenceApplication.class, args);
	}
}
