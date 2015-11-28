package com.synerzip.billfold.receiver.service;

import com.synerzip.billfold.receiver.dto.TransactionDTO;

public interface ReceiverService {

	public TransactionDTO createTransaction(TransactionDTO dto,Long receiver );
	
}
