package com.alushkja.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotCreateUserException extends RuntimeException{

	public CannotCreateUserException(String message) {
		super(message);
	}

}
