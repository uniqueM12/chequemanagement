package com.wizer.test.controller;

import com.wizer.test.model.User;
import com.wizer.test.service.UserServce;
import org.slf4j.Logger;
import com.wizer.test.model.Error;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tenece on 24/08/2019.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServce userServce;

    @PostMapping("/signup")
    public ResponseEntity<User> create(@RequestBody User user){

        logger.debug("processing signup details...");
logger.debug("Persisting:\n{}", user);
        Error error = null;
        String errorMessage = "";

        User savedUser = null;
        try {
            savedUser = userServce.save(user);
            logger.debug("User successfully saved");
            return new ResponseEntity(savedUser, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> findUser(@RequestBody User user){

        logger.debug("trying to find a user...");

        Error error = null;
        String errorMessage;

        User retrievedUser = null;
        try {
            retrievedUser = userServce.findById(user.getId());
            logger.debug("Search completed");
            return new ResponseEntity(retrievedUser, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            e.printStackTrace();
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(){

        logger.debug("trying to find a user...");

        Error error = null;
        String errorMessage = "";

        List<User> retrievedUsers = new ArrayList<>();
        try {
            retrievedUsers = userServce.findAll();
            logger.debug("Search completed");
            return new ResponseEntity(retrievedUsers, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
