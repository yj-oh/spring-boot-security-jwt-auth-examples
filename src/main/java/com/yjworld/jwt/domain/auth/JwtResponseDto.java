package com.yjworld.jwt.domain.auth;

import com.yjworld.jwt.config.jwt.JwtUtils;
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

	@ApiModelProperty(value = "토큰 타입", required = true)
	private final String tokenType = JwtUtils.TOKEN_TYPE;

	@ApiModelProperty(value = "토큰", required = true)
	private String token;
}
