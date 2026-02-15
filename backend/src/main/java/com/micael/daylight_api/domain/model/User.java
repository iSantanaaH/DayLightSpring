package com.micael.daylight_api.domain.model;

import com.micael.daylight_api.domain.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Boolean blocked;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    public User() {
    }

    public User(String name, String email, String password, LocalDate birthDate, String gender, Role role, Boolean active, Boolean blocked, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
        this.active = true;
        this.blocked = false;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
}
