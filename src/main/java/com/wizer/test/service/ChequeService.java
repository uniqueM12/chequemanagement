package com.wizer.test.service;

import com.wizer.test.model.Cheque;
import com.wizer.test.model.User;
import com.wizer.test.repository.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Tenece on 24/08/2019.
 */
@Service
public class ChequeService {

    @Autowired
    private ChequeRepository chequeRepository;

    @Autowired
    private UserServce userServce;

    public Cheque save(Cheque cheque){

        Cheque savedCheque = chequeRepository.save(cheque);

        return savedCheque;
    }

    public Cheque assign(Cheque cheque){

        User branchManager = userServce.findById(cheque.getBranchManager().getId());

        cheque = chequeRepository.findOneById(cheque.getId());
        //branchManager.setCheques(Arrays.asList(new

        cheque.setBranchManager(branchManager);
        cheque = chequeRepository.save(cheque);

        return cheque;
    }

    public List<Cheque> findAll(){

        List<Cheque> foundCheques = chequeRepository.findAll(Sort.by("id"));

        return foundCheques;
    }
}
