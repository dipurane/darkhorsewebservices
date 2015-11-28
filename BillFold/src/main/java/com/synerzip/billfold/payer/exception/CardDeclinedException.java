package com.synerzip.billfold.payer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_REQUIRED, reason = "Card declined please use valid card")
public class CardDeclinedException extends RuntimeException{

	public CardDeclinedException() {
		// TODO Auto-generated constructor stub
	}
}
