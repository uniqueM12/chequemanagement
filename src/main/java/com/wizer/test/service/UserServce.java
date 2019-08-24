package com.wizer.test.service;

import com.sun.media.sound.SoftTuning;
import com.wizer.test.controller.ChequeController;
import com.wizer.test.model.User;
import com.wizer.test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tenece on 24/08/2019.
 */

@Service
public class UserServce {

    Logger logger = LoggerFactory.getLogger(UserServce.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User save(User user){

        user.setPassword(passwordEncoder.encode(user.bringPassword()));
        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public User update(User user){

        System.out.println("now updateting");
        logger.debug("trying to update user with id: {}", user.getId());
        User updatedUser = userRepository.save(user);
        logger.debug("User update for id {} completed: {}", user.getId());

        return updatedUser;
    }

    public User findById(Long  id){

        logger.debug("Attempting to retrieve user by id");
        User retreivedUser = userRepository.findOneById(id);

        logger.debug("User search by i completed");
        return retreivedUser;
    }

    public List<User> findAll(){

        List<User> retreivedUsers = userRepository.findAll(Sort.by("id"));

        return retreivedUsers;
    }
}
