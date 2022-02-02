package com.yjworld.jwt.domain.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "01. 계정")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@ApiOperation(value = "회원 가입")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "유효하지 않은 비밀번호 형식"),
			@ApiResponse(code = 409, message = "이미 가입되어 있는 이메일 또는 사용자 이름")
	})
	@PostMapping("/signup")
	public ResponseEntity<HttpStatus> register(@Valid @RequestBody RegisterRequestDto request) {
		authService.register(request.getEmail(), request.getPassword(), request.getUsername());
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "로그인")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 400, message = "비밀번호가 일치하지 않음"),
			@ApiResponse(code = 404, message = "사용자 정보를 찾을 수 없음")
	})
	@PostMapping("/login")
	public JwtResponseDto login(@RequestBody LoginRequestDto request) {
		return authService.login(request);
	}
}
