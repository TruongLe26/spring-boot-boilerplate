package com.truonglq.demo.bootstrap;

import com.truonglq.demo.exceptions.AppException;
import com.truonglq.demo.exceptions.ErrorCode;
import com.truonglq.demo.models.entities.Role;
import com.truonglq.demo.models.entities.User;
import com.truonglq.demo.models.enums.RoleEnum;
import com.truonglq.demo.repositories.RoleRepository;
import com.truonglq.demo.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@DependsOn("roleSeeder")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        seedAdmin();
    }

    private void seedAdmin() {
        Optional<User> optionalAdmin = userRepository.findByUsername("admin");
        if (optionalAdmin.isPresent()) return;
//        Optional<Role> optionalAdminRole = roleRepository.findByName(RoleEnum.ADMIN);
//        if (optionalAdminRole.isEmpty()) return;
        Role adminRole = roleRepository.findByName(RoleEnum.ADMIN)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles(new HashSet<>(List.of(adminRole)))
                .build();
        userRepository.save(admin);
    }
}
