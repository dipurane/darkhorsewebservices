package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Couldnt process transaction as Receivers bank account is closed")
public class AccountClosedException extends RuntimeException{

	public AccountClosedException() {
		// TODO Auto-generated constructor stub
	}
}
