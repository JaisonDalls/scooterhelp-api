package com.nosaij.scooterhelp.repository;

import com.nosaij.scooterhelp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("""
            SELECT DISTINCT u
            FROM User u
            LEFT JOIN FETCH u.roles
            """)
    List<User> findAllWithRoles();

    boolean existsByEmail(String email);
}
