package com.prgr.main.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customVaildationErrorHandling(final MethodArgumentNotValidException exception){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), "Validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> userNotFoundErrorHandling(final UserNotFoundException exception){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), "User Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<?> productErrorHandling(final ProductException exception){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), "Product Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FeedbackNotFoundException.class)
	public ResponseEntity<?> feedbackNotFoundErrorHandling(final FeedbackNotFoundException exception){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), "Feedback Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<?> reviewNotFoundErrorHandling(final ReviewNotFoundException exception){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), "Review Error", exception.getMessage());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}
