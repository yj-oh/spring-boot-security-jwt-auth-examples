package com.yjworld.jwt.domain.auth;

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

	public void register(String email, String password, String username) {
		userService.checkForDuplicateEmail(email);
		userService.checkForDuplicateUsername(username);

		User newUser = User.builder()
				.email(email)
				.username(username)
				.password(passwordEncoder.encode(password))
				.build();

		userRepository.save(newUser);
	}
}
