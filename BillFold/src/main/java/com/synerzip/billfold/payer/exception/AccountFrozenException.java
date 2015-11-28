package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Transaction can not be processed as Receivers Bank account is frozen")
public class AccountFrozenException extends RuntimeException{

	public AccountFrozenException() {
		// TODO Auto-generated constructor stub
	}
}
