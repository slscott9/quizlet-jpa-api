package com.stuart.quizletjpaapigradle.controllers;


import com.stuart.quizletjpaapigradle.models.Folder;
import com.stuart.quizletjpaapigradle.models.Term;
import com.stuart.quizletjpaapigradle.models.User;
import com.stuart.quizletjpaapigradle.models.UserSet;
import com.stuart.quizletjpaapigradle.models.dto.UserResponse;
import com.stuart.quizletjpaapigradle.models.dto.requests.FolderRequest;
import com.stuart.quizletjpaapigradle.models.dto.requests.SetRequest;
import com.stuart.quizletjpaapigradle.repositories.FolderRepository;
import com.stuart.quizletjpaapigradle.repositories.SetRepository;
import com.stuart.quizletjpaapigradle.repositories.TermRepository;
import com.stuart.quizletjpaapigradle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {



    @Autowired
    UserRepository userRepository;

    @Autowired
    TermRepository termRepository;

    @Autowired
    SetRepository setRepository;

    @Autowired
    FolderRepository folderRepository;

    @GetMapping("/folders")
    public String getFolders() {
        return "folder list";
    }



    @GetMapping("/{userEmail}")
    public ResponseEntity<UserResponse> getData(@PathVariable String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if(!optionalUser.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

//        else if(optionalUser.get().getFolders() == null) {
//            return ResponseEntity.noContent().build();
//        }

        return ResponseEntity.ok(
                new UserResponse(
                        optionalUser.get().getFolders(),
                        optionalUser.get().getSets()

                ));
    }

    @PostMapping("/addFolders")
    public ResponseEntity<String> addFolders(@RequestBody FolderRequest folderRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(folderRequest.getEmail());

        if(!optionalUser.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }


        //Sets the user property for each folder in order for GET {userEmail} to send folders belongin to user email
        for(Folder folder : folderRequest.getFolders()){
            folder.setUser(optionalUser.get());
        }

        folderRepository.saveAll(folderRequest.getFolders());

        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PostMapping("/addSets")
    public ResponseEntity<String> addSets(@RequestBody SetRequest setRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(setRequest.getUserEmail());

        if(!optionalUser.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        for(UserSet set : setRequest.getUserSets()){
            set.setUser(optionalUser.get()); //set user for this set

            for(Term term : set.getTerms()){ //for each set's terms set their user and user set properties

                term.setUser(optionalUser.get());
                term.setUserSet(set);
            }
            setRepository.save(set); //inserting sets saves its terms into term table as well because of one to many relationship one user set to many terms


        }

        return new ResponseEntity(HttpStatus.CREATED);

    }
}
