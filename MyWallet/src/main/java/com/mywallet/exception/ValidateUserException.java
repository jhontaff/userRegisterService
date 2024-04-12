package com.mywallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidateUserException extends RuntimeException{

	
	@SuppressWarnings("unused")
	private String message;
	
	public ValidateUserException(String message) {
		super(message);
		
	}

}
