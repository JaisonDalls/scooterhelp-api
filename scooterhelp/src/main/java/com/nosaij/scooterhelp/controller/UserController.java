package com.nosaij.scooterhelp.controller;

import com.nosaij.scooterhelp.dto.*;
import com.nosaij.scooterhelp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> users(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto){
        UserResponseDTO response = userService.create(dto);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable("id") String id, @Valid @RequestBody UserUpdateDTO dto){
        return userService.updateUser(id, dto);
    }

    @PostMapping("/{id}/addresses")
    public void addAddress(
            @PathVariable String id,
            @RequestBody AddressRequestDTO dto) {

        userService.addAddress(id, dto);
    }

    @PutMapping("/{userId}/addresses/{addressId}")
    public void updateAddress(
            @PathVariable String userId,
            @PathVariable String addressId,
            @RequestBody AddressUpdateDTO dto) {

        userService.updateAddress(userId, addressId, dto);
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public void deleteAddress(
            @PathVariable String userId,
            @PathVariable String addressId) {

        userService.removeAddress(userId, addressId);
    }

}
