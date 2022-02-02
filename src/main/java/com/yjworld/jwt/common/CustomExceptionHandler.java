package com.yjworld.jwt.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * status 400
	 * MethodArgumentNotValidException (@Valid failed)
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
																  final HttpHeaders headers,
																  final HttpStatus status,
																  final WebRequest request) {

		CommonError commonError = CommonError.of(ex.getClass().getSimpleName(),
				HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getBindingResult());
		return handleExceptionInternal(ex, commonError, headers, commonError.getStatus(), request);
	}

	/**
	 * status 400
	 * BindException – This exception is thrown when fatal binding errors occur.
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex,
														 final HttpHeaders headers,
														 final HttpStatus status,
														 final WebRequest request) {
		CommonError commonError = CommonError.of(ex.getClass().getSimpleName(),
				HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), ex.getBindingResult());
		return handleExceptionInternal(ex, commonError, headers, commonError.getStatus(), request);
	}

	/**
	 * status 500
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(final Exception ex) {
		CommonError commonError = CommonError.of(ex.getClass().getSimpleName(),
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());

		return new ResponseEntity<>(commonError, new HttpHeaders(), commonError.getStatus());
	}
}
