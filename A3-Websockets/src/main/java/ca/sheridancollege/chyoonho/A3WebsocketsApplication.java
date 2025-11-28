package ca.sheridancollege.chyoonho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class A3WebsocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(A3WebsocketsApplication.class, args);
	}

}
