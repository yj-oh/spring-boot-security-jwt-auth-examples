package com.yjworld.jwt.domain.sample;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
public class SampleController {

	@ApiOperation(value = "테스트용 샘플 API")
	@GetMapping
	public String test() {
		return "success";
	}
}
