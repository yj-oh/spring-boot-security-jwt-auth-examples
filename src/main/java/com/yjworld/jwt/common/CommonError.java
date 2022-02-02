package com.yjworld.jwt.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CommonError {

	private String type;
	private HttpStatus status;
	private String message;
	private List<String> errors;

	public static CommonError of(final String type,
								 final HttpStatus status,
								 final String message) {
		return new CommonError(type, status, message, List.of(message));
	}

	public static CommonError of(final String type,
								 final HttpStatus status,
								 final String message,
								 final BindingResult bindingResult) {
		List<String> errors = new ArrayList<>();
		bindingResult.getFieldErrors()
				.forEach(error -> errors.add(error.getField() + ": " + error.getDefaultMessage()));
		bindingResult.getGlobalErrors()
				.forEach(error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

		return new CommonError(type, status, message, errors);
	}
}
