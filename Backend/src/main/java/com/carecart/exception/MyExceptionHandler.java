package com.carecart.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MyExceptionHandler {
	private LocalDateTime time;
	private String message;
	private String details;
}
