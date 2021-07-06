package com.cts.iptreatment.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class GlobalExceptionHandlerTest {
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	@Mock
	ExceptionDetails customErrorResponse;
	@BeforeEach
	public void setUp() {
		customErrorResponse = new ExceptionDetails(LocalDateTime.now(), "test message");
	}
	@Test
	public void handlesAuthorizationExceptionTest() {
		AuthorizationException e = new AuthorizationException("message");
		globalExceptionHandler.handleGlobalException(e,null);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}
	@Test
	public void handlesExceptionTest() {
		Exception e = new Exception("message");
		globalExceptionHandler.handleGlobalException(e, null);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}
	@Test
	public void handlesPatientNameAlreadyExistsExceptionTest() {
		PatientNameAlreadyExistsException e = new PatientNameAlreadyExistsException("message");
		globalExceptionHandler.handleGlobalException(e, null);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}
	@Test
	public void handlesIPTreatmentPackageNotFoundExceptionTest() {
		IPTreatmentPackageNotFoundException e = new IPTreatmentPackageNotFoundException("message");
		globalExceptionHandler.handleGlobalException(e, null);
		ResponseEntity<?> entity = new ResponseEntity<>(customErrorResponse, HttpStatus.UNAUTHORIZED);
		assertEquals(401, entity.getStatusCodeValue());
	}
}
