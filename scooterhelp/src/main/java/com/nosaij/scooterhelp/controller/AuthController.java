package com.nosaij.scooterhelp.controller;

import com.nosaij.scooterhelp.dto.LoginDTO;
import com.nosaij.scooterhelp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @PostMapping("/login")
    public String login( @RequestBody @Valid LoginDTO data){
        return service.login(data.email(), data.password());
    }
}
