package com.nosaij.scooterhelp.service;

import com.nosaij.scooterhelp.domain.Role;
import com.nosaij.scooterhelp.domain.User;
import com.nosaij.scooterhelp.dto.UserRequestDTO;
import com.nosaij.scooterhelp.dto.UserResponseDTO;
import com.nosaij.scooterhelp.repository.RoleRepository;
import com.nosaij.scooterhelp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserResponseDTO create(UserRequestDTO dto){
        List<Role> roles = dto.roles()
                .stream()
                .map(roleName -> roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Role not found."))).toList();

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .roles(roles)
                .password(dto.password())
                .build();

        User saved = userRepository.save(user);

        return toDTO(saved);
    }

    public List<UserResponseDTO> findAll(){
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UserResponseDTO findById(String id){
        return userRepository.findById(id).map(this::toDTO).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void delete(String id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public UserResponseDTO update(String id, UserResponseDTO dto){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

       List<Role> roles = dto.roles()
               .stream()
               .map(roleName -> roleRepository.findByName(roleName).orElseThrow(()->new RuntimeException("Role not found"))).toList();

       user = User.builder()
               .id(user.getId())
               .name(user.getName())
               .email(user.getEmail())
               .password(user.getPassword())
               .roles(roles)
               .build();

       return toDTO(user);
    }

    private UserResponseDTO toDTO(User user){
        List<String> roles = user.getRoles().stream().map(Role::getName).toList();

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), roles);
    }
}
