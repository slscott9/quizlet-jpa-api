package com.stuart.quizletjpaapigradle;

import com.stuart.quizletjpaapigradle.models.dto.requests.FolderRequest;
import com.stuart.quizletjpaapigradle.repositories.FolderRepository;
import com.stuart.quizletjpaapigradle.repositories.SetRepository;
import com.stuart.quizletjpaapigradle.repositories.TermRepository;
import com.stuart.quizletjpaapigradle.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, FolderRepository.class, SetRepository.class, TermRepository.class})
public class QuizletJpaApiGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizletJpaApiGradleApplication.class, args);
	}

}
