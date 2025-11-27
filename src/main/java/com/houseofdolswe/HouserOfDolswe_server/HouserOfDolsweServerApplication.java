package com.houseofdolswe.HouserOfDolswe_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HouserOfDolsweServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouserOfDolsweServerApplication.class, args);
	}

}
