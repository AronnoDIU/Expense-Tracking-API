package com.aronno.expensetracking_api.repository;

import com.aronno.expensetracking_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
