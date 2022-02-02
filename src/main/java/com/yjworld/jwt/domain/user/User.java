package com.yjworld.jwt.domain.user;

import com.yjworld.jwt.domain.common.AuditCreatedAndUpdated;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity
public class User extends AuditCreatedAndUpdated {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@Size(max = 50)
	private String email;

	@Column(nullable = false)
	@Size(max = 200)
	private String password;

	@Column(nullable = false, unique = true)
	@Size(max = 20)
	private String username;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role = Role.USER;

	@Column(columnDefinition = "timestamp null comment '탈퇴일시'")
	private LocalDateTime deletedAt;

	public User() {}

	@Builder
	public User(String email, String password, String username, Role role) {
		this.email = email;
		this.password = password;
		this.username = username;
		this.role = role;
	}
}
