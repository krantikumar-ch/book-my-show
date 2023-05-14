package com.examples.lld.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;

@SpringBootApplication
@EnableJpaRepositories(bootstrapMode = BootstrapMode.DEFAULT)
public class BookMyShowApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookMyShowApplication.class, args);
	}

}
