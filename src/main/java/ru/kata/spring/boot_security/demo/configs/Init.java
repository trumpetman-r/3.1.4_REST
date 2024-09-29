package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class Init {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public Init(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void init() {
        roleRepository.save(new Role(1L, "ROLE_ADMIN"));
        roleRepository.save(new Role(2L, "ROLE_USER"));
        userService.save(new User("admin", "admin", "admin", 55, "admin@admin.ru", "123456", roleRepository.findAll().stream().collect(Collectors.toSet())));
        userService.save(new User("user", "user", "user", 44, "user@user.ru", "32167", roleRepository.findById(2L).stream().collect(Collectors.toSet())));
    }

}