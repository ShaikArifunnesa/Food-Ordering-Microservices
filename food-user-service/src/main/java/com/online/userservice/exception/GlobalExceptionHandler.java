package com.online.userservice.exception;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> hanldeException(MethodArgumentNotValidException ex){
		List<FieldError> errors=ex.getFieldErrors();
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTime(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Input Data has some errors... Please Input proper data");
		
		for(FieldError fieldError : errors) {
		validationErrorResponse.getErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse, HttpStatus.BAD_REQUEST);
		}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ValidationErrorResponse> hanldeException(ConstraintViolationException ex){
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTime(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Input Data has some errors... Please Input proper data");
		ex.getConstraintViolations() . forEach(error ->{ 
			validationErrorResponse.getErrors().put("field",error.getMessage());
			});
		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<ErrorResponse> hanldeException(UserExistException ex){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	
}
	
	@ExceptionHandler(EmaiIdExistException.class)
	public ResponseEntity<ErrorResponse> hanldeException(EmaiIdExistException ex){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	
}
	@ExceptionHandler(PhoneNumberExistException.class)
	public ResponseEntity<ErrorResponse> hanldeException(PhoneNumberExistException ex){
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	
}
}
