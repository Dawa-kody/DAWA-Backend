package com.kody.dawa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DawaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DawaApplication.class, args);
	}

}
