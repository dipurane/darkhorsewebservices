package com.synerzip.billfold.payer.service;

import java.util.List;

import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;

public interface PayerService {

	public String generatePVCCode(Long userId);
	
	public List<CreditCardDTO> getAllCardsForPayer(Long userId);
	
	public TransactionDTO getOpenTransactionForPayer(Long userId);
}
