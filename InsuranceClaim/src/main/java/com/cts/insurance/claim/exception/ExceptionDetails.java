package com.cts.insurance.claim.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter @NoArgsConstructor 
public class ExceptionDetails {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;
	private String message;
	public ExceptionDetails(LocalDateTime timeStamp, String message) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
	}
}
