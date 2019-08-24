package com.wizer.test.repository;

import com.wizer.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

/**
 * Created by Tenece on 24/08/2019.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Nullable
    User findOneById(Long id);

    @Nullable
    User findOneByUsername(String username);
}
