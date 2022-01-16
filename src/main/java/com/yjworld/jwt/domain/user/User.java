package com.yjworld.jwt.domain.user;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String username;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role = Role.USER;
}
