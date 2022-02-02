package com.yjworld.jwt.domain.auth;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class RegisterRequestDto {

	@Email
	@NotBlank
	@Size(max = 50)
	private String email;

	@NotBlank
	@Size(min = 6, max = 50)
	private String password;

	@NotBlank
	@Size(min = 2, max = 20)
	private String username;
}
