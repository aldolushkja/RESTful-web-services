package com.alushkja.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alushkja.rest.webservices.restfulwebservices.exception.CannotCreateUserException;
import com.alushkja.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.alushkja.rest.webservices.restfulwebservices.exception.UsersNotFoundException;

//
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) 
			 {
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
			ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) 
			 {
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
			ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsersNotFoundException.class)
	public final ResponseEntity<Object> handleUsersNotFoundException(UsersNotFoundException ex, WebRequest request) 
			 {
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
			ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CannotCreateUserException.class)
	public final ResponseEntity<Object> handleCreateUserException(CannotCreateUserException ex, WebRequest request) 
			 {
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
			ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConflictCreateUserException.class)
	public final ResponseEntity<Object> handleCreateUserException(ConflictCreateUserException ex, WebRequest request) 
			 {
	ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
			ex.getMessage(), request.getDescription(false));
	return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
	}

}
