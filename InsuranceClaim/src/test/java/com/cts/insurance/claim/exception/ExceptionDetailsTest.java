package com.cts.insurance.claim.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExceptionDetailsTest {
	@BeforeEach
	void setUp() throws Exception {
	}
	@Test
	void test() {
		ExceptionDetails e = new ExceptionDetails();
		assertThat(e).isNotNull();
	}
	@Test
	void testSetter() {
		ExceptionDetails e = new ExceptionDetails();
		e.setMessage("Test message");
		assertThat(e.getMessage().equals("Test message"));
	}
	@Test
	void testSetter2() {
		ExceptionDetails e = new ExceptionDetails();
		LocalDateTime d = LocalDateTime.now();
		e.setTimeStamp(d);
		assertThat(e.getTimeStamp().equals(d));
	}
}
