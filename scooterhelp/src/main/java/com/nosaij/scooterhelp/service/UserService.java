package com.nosaij.scooterhelp.service;

import com.nosaij.scooterhelp.domain.Address;
import com.nosaij.scooterhelp.domain.Role;
import com.nosaij.scooterhelp.domain.User;
import com.nosaij.scooterhelp.dto.*;
import com.nosaij.scooterhelp.enums.RoleName;
import com.nosaij.scooterhelp.enums.UserType;
import com.nosaij.scooterhelp.exception.AddressNotFoundException;
import com.nosaij.scooterhelp.exception.UserAlreadyExistsException;
import com.nosaij.scooterhelp.exception.UserNotFoundException;
import com.nosaij.scooterhelp.repository.RoleRepository;
import com.nosaij.scooterhelp.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO create(UserRequestDTO dto){

        if(userRepository.existsByEmail(dto.email())){
            throw new UserAlreadyExistsException("Já existe um usuário com esse email!");
        }

        Set<Role> roles = resolveRolesByUserType(dto.userType());

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .roles(roles)
                .password(passwordEncoder.encode(dto.password()))
                .phone(dto.phone())
                .build();

        Address address = Address.builder()
                .street(dto.address().street())
                .neighborhood(dto.address().neighborhood())
                .number(dto.address().number())
                .city(dto.address().city())
                .state(dto.address().state())
                .complement(dto.address().complement())
                .zipCode(dto.address().zipCode())
                .build();

        user.addAddress(address);

        User saved = userRepository.save(user);

        return toDTO(saved);
    }

    public List<UserResponseDTO> findAll(){
        return userRepository.findAllWithRoles()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UserResponseDTO findById(String id){
        return userRepository.findById(id).map(this::toDTO).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
    }

    public void delete(String id){
        User user = getUser(id);
        userRepository.delete(user);
    }

    @Transactional
    public UserResponseDTO updateUser(String id, UserUpdateDTO dto) {

        User user = getUser(id);
        user.updateUserData(dto.name(), dto.email(), dto.phone());

        if (dto.password() != null) {
            user.changePassword(passwordEncoder.encode(dto.password()));
        }

        return toDTO(user);
    }

    @Transactional
    public void addAddress(String userId, AddressRequestDTO dto) {

        User user = getUser(userId);

        Address address = Address.builder()
                .street(dto.street())
                .city(dto.city())
                .state(dto.state())
                .zipCode(dto.zipCode())
                .number(dto.number())
                .neighborhood(dto.neighborhood())
                .complement(dto.complement())
                .build();

        user.addAddress(address);
    }

    @Transactional
    public void updateAddress(String userId, String addressId, AddressUpdateDTO dto) {
        User user = getUser(userId);
        user.updateAddress(addressId, dto);
    }

    @Transactional
    public void removeAddress(String userId, String addressId) {
        User user = getUser(userId);
        Address address = user.getAddresses().stream()
                .filter(a -> false)
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException("Endereço não encontrado!"));

        user.removeAddress(address);
    }

    private User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
        return user;
    }

    private UserResponseDTO toDTO(User user){
        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), roles);
    }

    private Set<Role> resolveRolesByUserType(UserType userType){
        RoleName primaryRole = userType.getRole();

        return primaryRole.getAllRoles()
                .stream()
                .map(roleName ->
                        roleRepository.findByName(roleName.name())
                                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName))
                )
                .collect(Collectors.toSet());
    }


    private Role getRole(RoleName roleName){
        return roleRepository.findByName(roleName.name()).orElseThrow(()->new RuntimeException("Role not found"));
    }
}
