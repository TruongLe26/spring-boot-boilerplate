package com.truonglq.demo.repositories;

import com.truonglq.demo.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Page<User> findByUsername(String username, Pageable pageable);

    @Query(value = "SELECT * FROM users AS u WHERE u.id < ?1 ORDER BY u.id DESC LIMIT ?2",
            nativeQuery = true)
    List<User> fetchAll(long id, int limit);

    //    @Query(value = "SELECT * FROM users WHERE username = :username",
//            countQuery = "SELECT count(*) FROM users WHERE username = :username",
//            nativeQuery = true)
//    Page<User> findByUsername(@Param("username") String username, Pageable pageable);
}
