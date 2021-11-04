package com.example.databaselink.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import com.example.databaselink.exception.domain.APIErrorResponseTemplate;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ResponseStatusException.class)
	public final ResponseEntity<APIErrorResponseTemplate> handleResponseStatusException(HttpServletRequest request,
			ResponseStatusException responseStatusException) {
		APIErrorResponseTemplate apiErrorResponseTemplate = new APIErrorResponseTemplate();
		apiErrorResponseTemplate.setMessage(responseStatusException.getReason());
		apiErrorResponseTemplate.setStatus(responseStatusException.getStatus().value());
		return ResponseEntity.status(responseStatusException.getStatus()).body(apiErrorResponseTemplate);
	}

}
