package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Couldnt create bank account due to incorrect details")
public class IncorrectAccountDetailException extends RuntimeException{

	public IncorrectAccountDetailException() {
		// TODO Auto-generated constructor stub
	}
}
