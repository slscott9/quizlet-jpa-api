package com.stuart.quizletjpaapigradle.repositories;

import com.stuart.quizletjpaapigradle.models.Term;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Long> {
}
