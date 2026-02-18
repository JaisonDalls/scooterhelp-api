package com.nosaij.scooterhelp.controller;

import com.nosaij.scooterhelp.dto.UserRequestDTO;
import com.nosaij.scooterhelp.dto.UserResponseDTO;
import com.nosaij.scooterhelp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto){
        UserResponseDTO response = userService.create(dto);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable String id, @Valid @RequestBody UserResponseDTO dto){
        return userService.update(id, dto);
    }
}
