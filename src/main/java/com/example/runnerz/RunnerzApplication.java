package com.example.runnerz;

import com.example.runnerz.run.Location;
import com.example.runnerz.run.Run;
import com.example.runnerz.run.RunRepository;
import com.example.runnerz.user.User;
import com.example.runnerz.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);

		log.info("Application started successfully!");
	}

//	@Bean
//	CommandLineRunner runner(RunRepository runRepository) {
//		return args -> {
//			Run run = new Run(2, "Second Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS),5, Location.OUTDOOR );
//			runRepository.create(run);
//
//			log.info("Run: " + run);
//		};
//	}

//	@Bean
//	CommandLineRunner runner(UserRestClient client) {
//		return args -> {
//			List<User> users = client.findAll();
//
//			System.out.println(users);
//		};
//	}

}
