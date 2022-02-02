package com.yjworld.jwt.domain.sample;

import com.yjworld.jwt.domain.auth.AuthService;
import com.yjworld.jwt.domain.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/samples")
public class SampleController {

	private final AuthService authService;

	@ApiOperation(value = "테스트용 샘플 API")
	@GetMapping
	public String test() {
		User user = authService.getCurrentUser();
		return "Hello, " + user.getUsername();
	}
}
