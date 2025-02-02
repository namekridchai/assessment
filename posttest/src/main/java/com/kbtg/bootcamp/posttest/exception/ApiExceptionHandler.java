package com.kbtg.bootcamp.posttest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = LotteryException.class)
	public ResponseEntity<Object> handleLotteryException(LotteryException lotteryException){
		ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
				lotteryException.getMessage(),
				HttpStatus.BAD_REQUEST,
				ZonedDateTime.now()
		);
		return new ResponseEntity<>(apiExceptionResponse,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<Object> handleUserException(UserNotFoundException userNotFoundException){
		ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
				userNotFoundException.getMessage(),
				HttpStatus.NOT_FOUND,
				ZonedDateTime.now()
		);
		return new ResponseEntity<>(apiExceptionResponse,HttpStatus.NOT_FOUND);
	}
}
