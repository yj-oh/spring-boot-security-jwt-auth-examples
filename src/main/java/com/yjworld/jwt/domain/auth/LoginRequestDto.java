package com.yjworld.jwt.domain.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequestDto {

	@ApiModelProperty(value = "이메일", required = true)
	@NotBlank
	private String email;

	@ApiModelProperty(value = "비밀번호", required = true)
	@NotBlank
	private String password;
}
