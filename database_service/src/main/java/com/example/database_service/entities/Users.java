package com.example.database_service.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "profile_name", nullable = false, unique = true)
    private String profileName;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "activation_code")
    private String activationCode;
}
