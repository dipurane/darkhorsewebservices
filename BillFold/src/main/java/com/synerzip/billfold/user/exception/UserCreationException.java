package com.synerzip.billfold.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "User Profile Can not be created due to server error")
public class UserCreationException extends RuntimeException{

	public UserCreationException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
