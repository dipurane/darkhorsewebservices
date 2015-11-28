package com.synerzip.billfold.receiver.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synerzip.billfold.receiver.dao.repo.TransactionRepository;
import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.receiver.entity.Transaction;
import com.synerzip.billfold.receiver.exception.OpenTransactionException;
import com.synerzip.billfold.receiver.service.ReceiverService;
import com.synerzip.billfold.user.dao.repo.UserProfileRepository;
import com.synerzip.billfold.user.entity.UserProfile;
import com.synerzip.billfold.util.BillfoldConstants;

@Component("receiverService")
public class ReceiverServiceImpl implements ReceiverService {

	@Autowired
	private UserProfileRepository userProfileRepo;
	
	private TransactionRepository transactionRepo;
	
	@Override
	public TransactionDTO createTransaction(TransactionDTO dto,Long userId ) {
		// TODO Auto-generated method stub
		UserProfile payerProfile = userProfileRepo.findByPhoneNumber(dto.getPayerPhoneNumber());
		UserProfile receiverProfile  = userProfileRepo.findById(userId);
		Transaction tx =  transactionRepo.findOpenTransactionForPayer(payerProfile.getId(), BillfoldConstants.TRANSACTION_OPEN);
		if(tx !=null){
			// throw open transaction error
			new OpenTransactionException("Payer already have open transaction");
			
		}else{
			
			 tx = new Transaction();
			 tx.setAmount(dto.getAmount());
			 tx.setChargeId(null);
			 tx.setCreatedBy(userId);
			 tx.setCreatedDate(new Date());
			 tx.setDescription(dto.getDescription());
			 tx.setPayerProfile(payerProfile);
			 tx.setReceiverProfile(receiverProfile);
			 tx.setRefundId(null);
			 tx.setStatus(BillfoldConstants.TRANSACTION_OPEN);
			 tx.setUpdatedBy(userId);
			 tx.setUpdatedDate(new Date());
			 tx = transactionRepo.save(tx);
			 dto.setId(tx.getId());
		}
		
		return dto;
	}

	
}
