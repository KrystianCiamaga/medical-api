package com.example.demo.entity;

import com.example.demo.entity.Address;
import com.example.demo.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    private String email;

    private String role;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated
    private Gender gender;

    private Long pesel;

    @ManyToOne
    private Address address;

}
