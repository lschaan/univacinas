package com.univacinas.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;

    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public boolean isAdmin() {
        return role.equals(UserRole.ADMIN);
    }

    public boolean isPatient() {
        return role.equals(UserRole.PATIENT);
    }

    public boolean isNurse() {
        return role.equals(UserRole.NURSE);
    }

}
