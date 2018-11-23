package com.alushkja.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictCreateUserException extends RuntimeException {

	public ConflictCreateUserException(String message) {
		super(message);
	}

}
