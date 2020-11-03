package com.stuart.quizletjpaapigradle.repositories;


import com.stuart.quizletjpaapigradle.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
