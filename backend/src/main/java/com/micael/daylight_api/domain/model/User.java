package com.micael.daylight_api.domain.model;

import com.micael.daylight_api.domain.enums.Role;
import com.micael.daylight_api.domain.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

	@Column(nullable = false)
	private LocalDate birthDate;

	@Column(nullable = false)
	private String gender;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	@Column(nullable = false)
	private boolean locked;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatus status;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public User() {
	}

	public User(String name,
				String email,
				String password,
				LocalDate birthDate,
				String gender,
				Role role) {

		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.gender = gender;
		this.role = role;
		this.status = UserStatus.ACTIVE;
		this.locked = false;
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

	public void setGender(String newGender) {
		this.gender = newGender;
	}

	public UserStatus getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
