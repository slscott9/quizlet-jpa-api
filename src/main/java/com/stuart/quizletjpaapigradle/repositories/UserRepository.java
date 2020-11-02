package com.stuart.quizletjpaapigradle.repositories;

import com.stuart.quizletjpaapigradle.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String userEmail);

}
