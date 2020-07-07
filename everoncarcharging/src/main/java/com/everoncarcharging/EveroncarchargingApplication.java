package com.everoncarcharging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EveroncarchargingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EveroncarchargingApplication.class, args);
	}

}
