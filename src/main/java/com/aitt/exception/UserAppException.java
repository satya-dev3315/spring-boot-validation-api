package com.aitt.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aitt.customException.UserNotFoundCustomException;

@RestControllerAdvice
public class UserAppException {

	//when we will send invalid request to save() method through postman , it will nt vaidate nicely and if down u will see
	//u will get an exception : "trace": "org.springframework.web.bind.MethodArgumentNotValidException: Validation failed
	//so to give good validation with all fields , we need to write exception handler class
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)   //return status as bad request
	@ExceptionHandler(MethodArgumentNotValidException.class)  //tell to spring boot that whenever ur gettin this exception execute this method
	public Map<String, String> handleInvalidException(MethodArgumentNotValidException exception){
		
		//now extract the exception msg n map it n return readable msg to end user, ie: extract that n put into below errorMap
		
		Map<String, String> errorMap = new HashMap<>();
		//from above argument (ie: exception), i will get all the fields which r nt valid,
		
		exception.getBindingResult().getFieldErrors().forEach(error ->{
			//getFieldErrors() returns a list so use for each.And get value n  put into errorMap
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
		
	}
	
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)   
	@ExceptionHandler(UserNotFoundCustomException.class)
	public Map<String, String> handleCustomException(UserNotFoundCustomException exception){
		
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("error msg", exception.getMessage());
		return errorMap;
	
	}
}
