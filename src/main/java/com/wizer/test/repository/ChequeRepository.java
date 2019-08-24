package com.wizer.test.repository;

import com.wizer.test.model.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

/**
 * Created by Tenece on 24/08/2019.
 */
public interface ChequeRepository extends JpaRepository<Cheque, Long>{

    @Nullable
    Cheque findOneById(Long id);
}
