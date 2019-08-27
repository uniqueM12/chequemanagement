package com.wizer.test.controller;

import com.wizer.test.model.Cheque;
import com.wizer.test.model.Error;
import com.wizer.test.model.User;
import com.wizer.test.service.ChequeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tenece on 24/08/2019.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChequeController {

    Logger logger = LoggerFactory.getLogger(ChequeController.class);

    @Autowired
    private ChequeService chequeServce;

    @PostMapping("/createcheque")
    public ResponseEntity<Cheque> create(@RequestBody Cheque cheque){

        logger.debug("processing cheque details...");

        Error error = null;
        String errorMessage = "";
        Cheque savedCheque = null;
        try {
            savedCheque = chequeServce.save(cheque);
            logger.debug("Cheque successfully saved");
            return new ResponseEntity(savedCheque, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            e.printStackTrace();
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/assign")
    public ResponseEntity<Cheque> assign(@RequestBody Cheque cheque){

        System.out.println("Assign");
        System.out.println(cheque.getId());
        System.out.println(cheque.getBranchManager().getId());
        logger.debug("Attempting to Assign cheque id:{} to User: {}...", cheque.getId(), cheque.getBranchManager().getId());

        Error error = null;
        String errorMessage = "";

        Cheque savedCheque = null;

        try {
            savedCheque = chequeServce.assign(cheque);
            logger.debug("Cheque has been successfuly saved");
            return new ResponseEntity(savedCheque, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            e.printStackTrace();
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/unassigned")
    public ResponseEntity<Cheque> unassigned(){

        logger.debug("Attempting to retrieve unassigned cheques");

        Error error = null;
        String errorMessage = "";

        List<Cheque> foundCheques = new ArrayList<>();

        try {
            foundCheques.addAll(chequeServce.findUnassigned());
            logger.debug("Cheque retrieval has been completed");
            return new ResponseEntity(foundCheques, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cheques")
    public ResponseEntity<List<Cheque>> cheques(){

        logger.debug("Attempting to retrieve all cheques");

        Error error = null;
        String errorMessage = "";

        List<Cheque> foundCheques = new ArrayList<>();

        try {
            foundCheques.addAll(chequeServce.findAll());
            logger.debug("Cheque retrieval has been completed");
            return new ResponseEntity(foundCheques, HttpStatus.OK);
        }catch (Exception e){
            logger.debug("An error has occurred");
            errorMessage = e.getMessage();
            error = new Error(1, errorMessage);
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
