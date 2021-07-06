package com.cts.authorization.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionDetails, status);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<Object> handleGlobalException(AuthorizationException ex, WebRequest request) {
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<?> handleSignatureException(SignatureException signatureException, WebRequest webRequest) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ExceptionDetails(LocalDateTime.now(), signatureException.getLocalizedMessage()));
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
//		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
//		log.error(ex.getMessage());
//		return new ResponseEntity<>(exceptionDetail, HttpStatus.UNAUTHORIZED);
//	}
}