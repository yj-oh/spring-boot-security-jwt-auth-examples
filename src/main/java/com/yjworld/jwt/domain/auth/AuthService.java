package com.yjworld.jwt.domain.auth;

import com.yjworld.jwt.config.jwt.JwtUtils;
import com.yjworld.jwt.domain.user.Role;
import com.yjworld.jwt.domain.user.User;
import com.yjworld.jwt.domain.user.UserRepository;
import com.yjworld.jwt.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final UserService userService;
	private final JwtUtils jwtUtils;

	public void register(String email, String password, String username) {
		userService.checkForDuplicateEmail(email);
		userService.checkForDuplicateUsername(username);

		User newUser = User.builder()
				.email(email)
				.username(username)
				.password(passwordEncoder.encode(password))
				.role(Role.USER)
				.build();

		userRepository.save(newUser);
	}

	public JwtResponseDto login(LoginRequestDto request) {
		User user = userService.getByEmailAndPassword(request.getEmail(), request.getPassword());
		String token = jwtUtils.generateToken(user);

		return JwtResponseDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.username(user.getUsername())
				.token(token)
				.build();
	}
}
