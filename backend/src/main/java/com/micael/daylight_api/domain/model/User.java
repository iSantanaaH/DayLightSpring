package com.micael.daylight_api.domain.model;

import com.micael.daylight_api.domain.enums.UserGender;
import com.micael.daylight_api.domain.enums.UserRole;
import com.micael.daylight_api.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(columnDefinition = "gender_type")
    private UserGender gender;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(nullable = false, columnDefinition = "user_role")
    private UserRole role;

    @Column(nullable = false)
    private boolean locked;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @CreationTimestamp
    @Column(nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    protected User() {
    }

    private User(String name,
                 String email,
                 String password,
                 String phone,
                 LocalDate birthdate,
                 UserRole role,
                 boolean locked) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthdate = birthdate;
        this.role = role;
        this.status = UserStatus.ACTIVE;
        this.locked = locked;
    }

    public static User create(String name, String email, String password, String phone, LocalDate birthdate) {
        return new User(
                name,
                email,
                password,
                phone,
                birthdate,
                UserRole.USER,
                false
        );
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setGender(UserGender newGender) {
        this.gender = newGender;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
