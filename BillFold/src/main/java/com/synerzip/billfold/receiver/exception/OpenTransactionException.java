package com.synerzip.billfold.receiver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Payer already has open transaciton")
public class OpenTransactionException extends RuntimeException{

	public OpenTransactionException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
