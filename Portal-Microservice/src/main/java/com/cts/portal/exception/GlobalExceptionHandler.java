package com.cts.portal.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request){
		log.error("handles by");
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(IPTreatmentPackageNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleIPTreatmentPackageNotFoundException(IPTreatmentPackageNotFoundException ex){
		log.error("handles by IPTreatmentPackageNotFoundException");
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PatientNameAlreadyExistsException.class)
	public ResponseEntity<ExceptionDetails> handlePatientNameAlreadyExists(PatientNameAlreadyExistsException ex){
		log.error("handles by PatientNameAlreadyExistsException");
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(FeignException.class)
    public ResponseEntity<ExceptionDetails> handleFeignStatusException(FeignException ex, HttpServletResponse response) {
		log.error("handles by handleFeignStatusException");
		ExceptionDetails exceptionDetail = new ExceptionDetails(LocalDateTime.now(), ex.getMessage());
		log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionDetail, HttpStatus.BAD_REQUEST);
    }
}