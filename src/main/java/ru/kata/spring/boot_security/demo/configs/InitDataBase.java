package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDataBase {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public InitDataBase(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void initDataBase() {
        Role adminRole = new Role(1L, "ROLE_ADMIN");
        Role userRole = new Role(2L, "ROLE_USER");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        Set<Role> adminRoles = new HashSet<>(Collections.singletonList(adminRole));
        Set<Role> userRoles = new HashSet<>(Collections.singletonList(userRole));

        userService.save(new User(
                "admin",
                "Max",
                "Ivanov",
                40,
                "ivanov@mail.ru",
                "root",
                adminRoles
        ));

        userService.save(new User(
                "user",
                "Sergey",
                "Petrov",
                25,
                "petrov@mail.ru",
                "root",
                userRoles
        ));
    }
}