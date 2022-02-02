package com.yjworld.jwt.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// 인증에 실패할 경우 Exception handling - Filter 에서 발생하는 에러이므로 따로 처리해준다.
	@Override
	public void commence(HttpServletRequest request,
						 HttpServletResponse response,
						 AuthenticationException ex) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Map<String, Object> data = new HashMap<>();
		data.put("type", ex.getClass().getSimpleName());
		data.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		data.put("error", "Unauthorized");
		data.put("message", ex.getMessage());

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), data);
	}
}
