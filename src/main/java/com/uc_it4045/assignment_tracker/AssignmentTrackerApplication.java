package com.uc_it4045.assignment_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AssignmentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentTrackerApplication.class, args);
	}

}
