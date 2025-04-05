package com.aronno.expensetracking_api.repository;

import com.aronno.expensetracking_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks if a user exists by their email address.
     *
     * @param email the email address of the user
     * @return true if the user exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Retrieves a user by their email address.
     *
     * @param email the email address of the user
     * @return the user with the specified email address
     */
    User findByEmail(String email);
}
