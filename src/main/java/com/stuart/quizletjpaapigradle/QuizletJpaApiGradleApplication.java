package com.stuart.quizletjpaapigradle;

import com.stuart.quizletjpaapigradle.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class QuizletJpaApiGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizletJpaApiGradleApplication.class, args);
	}

}
