package com.shopzone.app.exceptions;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String string) {
		 super(string);
	}

}
