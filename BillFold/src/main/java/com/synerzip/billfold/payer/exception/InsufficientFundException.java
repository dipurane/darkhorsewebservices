package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Insufficient fund to process transaction")
public class InsufficientFundException extends RuntimeException{

	public InsufficientFundException() {
		// TODO Auto-generated constructor stub
	}
}
