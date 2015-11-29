package com.synerzip.billfold.receiver.service;

import java.util.List;

import com.synerzip.billfold.receiver.dto.TransactionDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReceiverService.
 */
public interface ReceiverService {

	/**
	 * Creates the transaction.
	 *
	 * @param dto the dto
	 * @param receiver the receiver
	 * @return the transaction dto
	 */
	public TransactionDTO createTransaction(TransactionDTO dto, Long receiver);

	/**
	 * Gets the transaction by id.
	 *
	 * @param transactionId the transaction id
	 * @return the transaction by id
	 */
	public TransactionDTO getTransactionById(Long transactionId);

	/**
	 * Gets the transaction list.
	 *
	 * @param userId the user id
	 * @return the transaction list
	 */
	public List<TransactionDTO> getTransactionList(Long userId);
}
