package com.nosaij.scooterhelp.domain;

import com.nosaij.scooterhelp.dto.AddressRequestDTO;
import com.nosaij.scooterhelp.dto.AddressUpdateDTO;
import com.nosaij.scooterhelp.exception.AddressLimitExceededException;
import com.nosaij.scooterhelp.exception.AddressNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Builder.Default
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<>();

    // =========================
    // USER ACTIONS
    // =========================

    public void updateUserData(String name,String email, String phone) {
        if (name != null) this.name = name;
        if (phone != null) this.phone = phone;
        if(email != null) this.email = email;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // =========================
    // ADDRESS ACTIONS
    // =========================

    public void addAddress(Address address) {
        if (this.addresses.size() >= 3){
            throw new AddressLimitExceededException("Você já atingiu a quantidade máxima de endereços disponíveis para cadastro!");
        }

        address.setUser(this);
        this.addresses.add(address);
    }

    public void removeAddress(Address address){
        address.setUser(null);
        this.addresses.remove(address);
    }

    public void updateAddress(String addressId, AddressUpdateDTO updateData){
        Address existing = this.addresses
                .stream()
                .filter(a -> a.getId().equals(updateData.id()))
                .findFirst()
                .orElseThrow(()-> new AddressNotFoundException("Endereço não encontrado!"));

        existing.update(
                updateData.id(),
                updateData.street(),
                updateData.city(),
                updateData.state(),
                updateData.complement(),
                updateData.number(),
                updateData.neighborhood(),
                updateData.zipCode()
                );
    }


}
