package com.synerzip.billfold.stripe.service;

import com.synerzip.billfold.stripe.dto.BankAccountDTO;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;

public interface StripeService {

	public BankAccountDTO registerBankAccount(BankAccountDTO dto,Long userId);
	
	public CreditCardDTO registerCreditCard(CreditCardDTO dto , Long userId);
	
}
