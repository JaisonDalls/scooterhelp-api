package com.nosaij.scooterhelp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "addresses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = true)
    private String zipCode;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void update(
            String id,
            String street,
            String city,
            String state,
            String complement,
            String number,
            String neighborhood,
            String zipCode
    ){
        if(street != null) this.street = street;
        if(city != null) this.city = city;
        if(state != null) this.state = state;
        if(complement != null) this.complement = complement;
        if(number != null) this.number = number;
        if(neighborhood != null) this.neighborhood = neighborhood;
        if(zipCode != null) this.zipCode = zipCode;
    }

}
