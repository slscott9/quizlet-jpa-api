package com.stuart.quizletjpaapigradle.controllers;


import com.stuart.quizletjpaapigradle.models.Folder;
import com.stuart.quizletjpaapigradle.models.Term;
import com.stuart.quizletjpaapigradle.models.User;
import com.stuart.quizletjpaapigradle.models.UserSet;
import com.stuart.quizletjpaapigradle.models.dto.UserResponse;
import com.stuart.quizletjpaapigradle.models.dto.requests.SetRequest;
import com.stuart.quizletjpaapigradle.models.dto.requests.responses.ServerResponse;
import com.stuart.quizletjpaapigradle.repositories.FolderRepository;
import com.stuart.quizletjpaapigradle.repositories.SetRepository;
import com.stuart.quizletjpaapigradle.repositories.TermRepository;
import com.stuart.quizletjpaapigradle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.Optional;
import java.util.Set;

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


    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();

        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA : ");


        return filter;
    }



    @GetMapping("/{userEmail}")
    public ResponseEntity<UserResponse> getData(@PathVariable String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if(!optionalUser.isPresent()){
            return  ResponseEntity.unprocessableEntity().build();
        }

//        else if(optionalUser.get().getFolders() == null) {
//            return ResponseEntity.noContent().build();
//        }

//        return ResponseEntity.ok(
//                new UserResponse(
//                        optionalUser.get().getFolders(),
//                        optionalUser.get().getSets()
//
//                ));
        return new ResponseEntity<>(new UserResponse(optionalUser.get().getFolders(), optionalUser.get().getSets()), HttpStatus.OK);


    }



    @PostMapping("/addSets")
    public ResponseEntity<String> addSets(@RequestBody SetRequest setRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(setRequest.getUserEmail());

        if(!optionalUser.isPresent()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        for(UserSet set : setRequest.getUserSets()){
            set.setUser(optionalUser.get()); //set user for this set
            set.setIsSynced(true);

            for(Term term : set.getTerms()){ //for each set's terms set their user and user set properties

                term.setUser(optionalUser.get());
                term.setUserSet(set);
                term.setSynced(true);
            }
            setRepository.save(set); //inserting sets saves its terms into term table as well because of one to many relationship one user set to many terms


        }

        return new ResponseEntity(HttpStatus.CREATED);

    }


    @PostMapping("/addSet/terms/{userEmail}")
    public ServerResponse addSetTerms(@RequestBody Set<UserSet> userSets, @PathVariable String userEmail){

        System.out.println(userEmail.toString());
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        if(!optionalUser.isPresent()){
            return new ServerResponse("User does not exist", false);

        }

        for(UserSet set : userSets){
            set.setUser(optionalUser.get());
            set.setIsSynced(true);


            for(Term term : set.getTerms()){
                term.setUser(optionalUser.get());
                term.setUserSet(set);
                term.setSynced(true);
            }
        }

        setRepository.saveAll(userSets);

        return new ServerResponse("Successfully added sets", true);

    }

    @PostMapping("/addFolder/{userEmail}")
    public ResponseEntity<Folder> addFolder(@RequestBody Folder folder, @PathVariable String userEmail){
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);

        System.out.println(optionalUser.get().getEmail().toString());

        if(!optionalUser.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        folder.setUser(optionalUser.get());

        Folder savedFolder = folderRepository.save(folder);

        return new ResponseEntity<Folder>( savedFolder, HttpStatus.OK);
    }






}
