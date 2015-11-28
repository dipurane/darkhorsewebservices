package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Receivers Bank couldnt process transaction")
public class BankProcessException extends RuntimeException{

	public BankProcessException() {
		// TODO Auto-generated constructor stub
	}
}
