package com.stuart.quizletjpaapigradle.controllers;


import com.stuart.quizletjpaapigradle.models.User;
import com.stuart.quizletjpaapigradle.models.dto.UserDto;
import com.stuart.quizletjpaapigradle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public  RegisterController(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody UserDto userDto){
        User user = new User(
                userDto.getEmail(),
                userDto.getUserName(),
                passwordEncoder.encode(userDto.getPassword()),
                true,
                "USER",
                userDto.getFolders(),
                userDto.getSets()
        );

        userRepository.save(user);
    }
}
