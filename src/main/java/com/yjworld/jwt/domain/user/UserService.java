package com.yjworld.jwt.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public void checkForDuplicateEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);

		if (user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	public void checkForDuplicateUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}

	public User getByEmailAndPassword(String email, String password) {
		User user = userRepository.findUserByEmailAndDeletedAtIsNull(email)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return user;
	}
}
