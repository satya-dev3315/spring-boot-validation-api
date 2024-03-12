package com.aitt.customException;

public class UserNotFoundCustomException extends RuntimeException {

	public UserNotFoundCustomException(String message) {
		super(message);
	}

	
}
