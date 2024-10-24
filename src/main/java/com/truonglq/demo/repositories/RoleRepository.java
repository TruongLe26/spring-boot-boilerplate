package com.truonglq.demo.repositories;

import com.truonglq.demo.models.entities.Role;
import com.truonglq.demo.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(RoleEnum name);
}
