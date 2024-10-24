package com.truonglq.demo.bootstrap;

import com.truonglq.demo.models.entities.Role;
import com.truonglq.demo.models.enums.RoleEnum;
import com.truonglq.demo.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        seedRoles();
    }

    private void seedRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.ADMIN, RoleEnum.USER };
        Arrays.stream(roleNames).forEach(
                roleName -> {
                    Optional<Role> optionalRole = roleRepository.findByName(roleName);
                    optionalRole.ifPresentOrElse(System.out::println, () -> {
                        Role roleToCreate = Role.builder().name(roleName).build();
                        roleRepository.save(roleToCreate);
                    });
                }
        );

    }
}
