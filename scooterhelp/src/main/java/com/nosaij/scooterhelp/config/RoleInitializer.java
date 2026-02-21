package com.nosaij.scooterhelp.config;

import com.nosaij.scooterhelp.domain.Role;
import com.nosaij.scooterhelp.enums.RoleName;
import com.nosaij.scooterhelp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args){
        for (RoleName roleName : RoleName.values()) {
            roleRepository.findByName(roleName.name())
                    .orElseGet(()->
                            roleRepository.save(
                                    Role.builder()
                                            .name(roleName.name())
                                            .build()
                            )
                    );
        }
        System.out.println("Roles sincronizadas com sucesso!");
    }
}
