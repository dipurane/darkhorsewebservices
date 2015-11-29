package com.synerzip.billfold.payer.service;

import java.util.List;

import com.synerzip.billfold.payer.dto.PaymentActionDTO;
import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface PayerService.
 */
public interface PayerService {

	/**
	 * Generate pvc code.
	 *
	 * @param userId the user id
	 * @return the string
	 */
	public String generatePVCCode(Long userId);

	/**
	 * Gets the all cards for payer.
	 *
	 * @param userId the user id
	 * @return the all cards for payer
	 */
	public List<CreditCardDTO> getAllCardsForPayer(Long userId);

	/**
	 * Gets the open transaction for payer.
	 *
	 * @param userId the user id
	 * @return the open transaction for payer
	 */
	public TransactionDTO getOpenTransactionForPayer(Long userId);

	/**
	 * Process transaction.
	 *
	 * @param transactionId the transaction id
	 * @param dto the dto
	 * @return the transaction dto
	 */
	public TransactionDTO processTransaction(Long transactionId,
			PaymentActionDTO dto);
	
	/**
	 * Gets the transaction list.
	 *
	 * @param userId the user id
	 * @return the transaction list
	 */
	public List<TransactionDTO> getTransactionList(Long userId);
}
