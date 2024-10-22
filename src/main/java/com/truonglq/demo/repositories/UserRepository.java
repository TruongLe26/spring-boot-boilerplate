package com.truonglq.demo.repositories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.truonglq.demo.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
//    Page<User> findByUsername(String username, Pageable pageable);
    @Query(value = "SELECT * FROM users WHERE username = :username",
            countQuery = "SELECT count(*) FROM users WHERE username = :username",
            nativeQuery = true)
    Page<User> findByUsername(@Param("username") String username, Pageable pageable);
}
