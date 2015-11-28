/*
 * Copyright (C) 2015 Paywize. All Rights Reserved.
 *
 */
package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Represents User Profile with given phone number already exists.
 *
 * @author rohitghatol
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Credit Card Details are Missing")
public class MissingCreditCardException extends RuntimeException {

	/**
	 * Instantiates a new user not found exception.
	 */
	public MissingCreditCardException(){
		
	}
	
	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param msg the msg
	 */
	public MissingCreditCardException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
