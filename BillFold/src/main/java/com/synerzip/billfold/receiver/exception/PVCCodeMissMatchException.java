package com.synerzip.billfold.receiver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED, reason = "Payer already has open transaciton")
public class PVCCodeMissMatchException extends RuntimeException{

	public PVCCodeMissMatchException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
}
