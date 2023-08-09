package com.restaurante.reviews.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "full_name", nullable = false)
    protected String fullName;

    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @Column(name = "password", nullable = false)
    protected String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected UserStatus userStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected UserType userType;
}
