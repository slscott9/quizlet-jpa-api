package com.stuart.quizletjpaapigradle.repositories;

import com.stuart.quizletjpaapigradle.models.UserSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetRepository extends JpaRepository<UserSet, Long> {
}
