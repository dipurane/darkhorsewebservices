package com.synerzip.billfold.receiver.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synerzip.billfold.payer.dao.repo.PayerVerificationCodeRepository;
import com.synerzip.billfold.payer.entity.PayerVerificationCode;
import com.synerzip.billfold.receiver.dao.repo.TransactionRepository;
import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.receiver.entity.Transaction;
import com.synerzip.billfold.receiver.exception.OpenTransactionException;
import com.synerzip.billfold.receiver.exception.PVCCodeMissMatchException;
import com.synerzip.billfold.receiver.service.ReceiverService;
import com.synerzip.billfold.user.dao.repo.UserProfileRepository;
import com.synerzip.billfold.user.entity.UserProfile;
import com.synerzip.billfold.util.BillfoldConstants;

@Component("receiverService")
public class ReceiverServiceImpl implements ReceiverService {

	@Autowired
	private UserProfileRepository userProfileRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	@Autowired
	private PayerVerificationCodeRepository payerVerificationRepo;
	
	@Override
	public TransactionDTO createTransaction(TransactionDTO dto,Long userId ) {
		// TODO Auto-generated method stub
		UserProfile payerProfile = userProfileRepo.findByPhoneNumber(dto.getPayerPhoneNumber());
		UserProfile receiverProfile  = userProfileRepo.findById(userId);
		Transaction tx =  transactionRepo.findOpenTransactionForPayer(payerProfile.getId(), BillfoldConstants.TRANSACTION_OPEN);
		
		if(tx !=null){
			// throw open transaction error
			throw new OpenTransactionException("Payer already have open transaction");
			
		}else{
			  
			 PayerVerificationCode code = payerVerificationRepo.findByPayer(payerProfile);
			 if(code.getCode().equals(dto.getPayerBVCCode())){
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
			 }else{
				  throw new PVCCodeMissMatchException("BVC code not matching");
			 }	
		}
		
		return dto;
	}

	@Override
	public TransactionDTO getTransactionById(Long transactionId) {
		// TODO Auto-generated method stub
		Transaction tx =  transactionRepo.findOne(transactionId);
		TransactionDTO dto = new TransactionDTO();
		dto.setId(tx.getId());
		dto.setStatus(tx.getStatus());
		return dto;
	}

	@Override
	public List<TransactionDTO> getTransactionList(Long userId) {
		// TODO Auto-generated method stub
			UserProfile profile = userProfileRepo.findById(userId);
			List<Transaction> txList = transactionRepo.findByReceiverProfile(profile);
			List<TransactionDTO> dtoList = new ArrayList<TransactionDTO>();
			for (Transaction tx : txList) {
				TransactionDTO dto = new TransactionDTO();
				dto.setId(tx.getId());
				dto.setStatus(tx.getStatus());
				dto.setPayerPhoneNumber(tx.getPayerProfile().getPhoneNumber());
				dto.setAmount(tx.getAmount());
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String str = format.format(tx.getCreatedDate());
				dto.setCreatedDateStr(str);
				dtoList.add(dto);
			}
			return dtoList;
	}

	
}
