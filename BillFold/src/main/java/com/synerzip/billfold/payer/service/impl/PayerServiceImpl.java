package com.synerzip.billfold.payer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synerzip.billfold.payer.dao.repo.PayerVerificationCodeRepository;
import com.synerzip.billfold.payer.entity.PayerVerificationCode;
import com.synerzip.billfold.payer.exception.NoPendingTransactionException;
import com.synerzip.billfold.payer.service.PayerService;
import com.synerzip.billfold.receiver.dao.repo.TransactionRepository;
import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.receiver.entity.Transaction;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;
import com.synerzip.billfold.stripe.entity.StripeAccount;
import com.synerzip.billfold.stripe.entity.UserCreditCard;
import com.synerzip.billfold.user.dao.repo.UserProfileRepository;
import com.synerzip.billfold.user.entity.UserProfile;
import com.synerzip.billfold.util.BillfoldConstants;

@Component("payerService")
public class PayerServiceImpl implements PayerService{

	@Autowired
	private PayerVerificationCodeRepository pvcRepo;
	
	@Autowired
	private UserProfileRepository userRepo;
	
	@Autowired
	private TransactionRepository txRepo;
	
	@Override
	@Transactional
	public String generatePVCCode(Long userId) {
		// TODO Auto-generated method stub
		UserProfile profile = userRepo.findById(userId);
		PayerVerificationCode pvccode = pvcRepo.findByPayer(profile);
		String code = String.valueOf(new Random().nextInt(900) + 100);
		if(pvccode == null){
			pvccode =  new PayerVerificationCode();
		
			pvccode.setPayer(profile);
			pvccode.setCode(code);
		}
		pvccode.setCode(code);
		pvcRepo.save(pvccode);
		return pvccode.getCode();
	}

	public List<CreditCardDTO> getAllCardsForPayer(Long userId) {
		// TODO Auto-generated method stub
		UserProfile profile = userRepo.findById(userId);
		 StripeAccount account = profile.getStripeAccount().iterator().next();
		 Set<UserCreditCard> cards = account.getCardList();
		 List<CreditCardDTO> dtoList = new ArrayList<CreditCardDTO>();
		 for (UserCreditCard card : cards) {
		     CreditCardDTO dto = new CreditCardDTO();
		     dto.setId(card.getId());
		     dto.setLastFourDigits(card.getLastFourDigits());
		     dto.setCardType(card.getCardType());
		     dtoList.add(dto);
		 }
		
		return dtoList;
	}

	@Override
	public TransactionDTO getOpenTransactionForPayer(Long userId) {
		// TODO Auto-generated method stub
		Transaction tx =   txRepo.findOpenTransactionForPayer(userId, BillfoldConstants.TRANSACTION_OPEN);
		if(tx == null){
			throw new NoPendingTransactionException("No Pending Transaction");
		}
		TransactionDTO dto = new TransactionDTO();
		dto.setAmount(tx.getAmount());
		dto.setDescription(tx.getDescription());
		dto.setStatus(tx.getStatus());
		dto.setReceiverPhoneNumber(tx.getReceiverProfile().getPhoneNumber());
		return dto;
	}

}
