package com.nosaij.scooterhelp.controller;

import com.nosaij.scooterhelp.domain.User;
import com.nosaij.scooterhelp.dto.UserCreateDTO;
import com.nosaij.scooterhelp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public User create(@RequestBody @Valid UserCreateDTO data) {
        return service.create(data);
    }
}
