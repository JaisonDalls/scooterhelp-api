package com.nosaij.scooterhelp;

import com.nosaij.scooterhelp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScooterhelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScooterhelpApplication.class, args);
	}

    @Bean
    CommandLineRunner debug(UserRepository repository){
        return args -> {
          System.out.println("Total users no banco: " + repository.count());
        };
    }

}
