package com.stuart.quizletjpaapigradle.controllers;


import com.stuart.quizletjpaapigradle.models.User;
import com.stuart.quizletjpaapigradle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    UserRepository userRepository

    @GetMapping("/folders")
    public String getFolders() {
        return "folder list";
    }

    @GetMapping("/{email}")
    public User getData(@PathVariable String userEmail) {
        Optional<User> optionalUser = userRepository.
    }
}
