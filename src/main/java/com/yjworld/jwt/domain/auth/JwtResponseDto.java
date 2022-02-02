package com.yjworld.jwt.domain.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto {

	@ApiModelProperty(value = "ID", required = true)
	private Long id;

	@ApiModelProperty(value = "이메일", required = true)
	private String email;

	@ApiModelProperty(value = "사용자 이름", required = true)
	private String username;

	@ApiModelProperty(value = "토큰", required = true)
	private String token;
}
