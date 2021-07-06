package com.cts.insurance.claim.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetails, status);
	}
	@ExceptionHandler(IPTreatmentPackageNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleIPTreatmentPackageNotFoundException(IPTreatmentPackageNotFoundException exception) {
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(PatientNameAlreadyExistsException.class)
	public ResponseEntity<ExceptionDetails> handlePatientNameAlreadyExistsException(PatientNameAlreadyExistsException exception) {
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ExceptionDetails> handleAuthorizationException(AuthorizationException exception) {
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionDetails> handleFeignStatusException(FeignException exception, HttpServletResponse response) {
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception exception, WebRequest request) {
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), exception.getMessage());
		log.error(exception.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}