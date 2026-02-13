package com.nosaij.scooterhelp.service;

import com.nosaij.scooterhelp.domain.User;
import com.nosaij.scooterhelp.dto.LoginDTO;
import com.nosaij.scooterhelp.dto.LoginResponseDTO;
import com.nosaij.scooterhelp.dto.UserCreateDTO;
import com.nosaij.scooterhelp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public User create(UserCreateDTO data){
        if (repository.findByEmail(data.email()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(data.name())
                .email(data.email())
                .password(passwordEncoder.encode(data.password()))
                .role(data.role())
                .build();

        return repository.save(user);
    }

    public String login(String email, String password){
        User user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        return tokenService.generateToken(user);
    }
}
