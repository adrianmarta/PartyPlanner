package com.example.PartyPlanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PartyPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyPlannerApplication.class, args);
	}

}
