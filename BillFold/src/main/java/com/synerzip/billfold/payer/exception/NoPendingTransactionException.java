package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No transaction open for Payer")
public class NoPendingTransactionException extends RuntimeException{

	public NoPendingTransactionException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
