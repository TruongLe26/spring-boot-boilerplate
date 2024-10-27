package com.truonglq.demo.repositories;

import com.truonglq.demo.models.entities.Role;
import com.truonglq.demo.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
    List<Role> findByUsers_Username(String username);
}
