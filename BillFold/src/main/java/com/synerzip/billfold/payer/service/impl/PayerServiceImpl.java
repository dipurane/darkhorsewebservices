package com.synerzip.billfold.payer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;














import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.exception.CardException;
import com.stripe.model.Charge;
import com.synerzip.billfold.payer.dao.repo.PayerVerificationCodeRepository;
import com.synerzip.billfold.payer.dto.PaymentActionDTO;
import com.synerzip.billfold.payer.entity.PayerVerificationCode;
import com.synerzip.billfold.payer.exception.AccountClosedException;
import com.synerzip.billfold.payer.exception.AccountFrozenException;
import com.synerzip.billfold.payer.exception.BankProcessException;
import com.synerzip.billfold.payer.exception.BillFoldException;
import com.synerzip.billfold.payer.exception.IncorrectAccountDetailException;
import com.synerzip.billfold.payer.exception.InsufficientFundException;
import com.synerzip.billfold.payer.exception.MissingCreditCardException;
import com.synerzip.billfold.payer.exception.NoPendingTransactionException;
import com.synerzip.billfold.payer.service.PayerService;
import com.synerzip.billfold.receiver.dao.repo.TransactionRepository;
import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.receiver.entity.Transaction;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;
import com.synerzip.billfold.stripe.entity.StripeAccount;
import com.synerzip.billfold.stripe.entity.UserCreditCard;
import com.synerzip.billfold.stripe.util.StripeUtil;
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
	
	@Autowired
	private StripeUtil stripeUtil;
	
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

	@Override
	public TransactionDTO processTransaction(Long transactionId,
			PaymentActionDTO dto, Boolean useProduction) {
		// TODO Auto-generated method stub
		Transaction tx = txRepo.findOne(transactionId);
		try {
			UserProfile payer = tx.getPayerProfile();
			UserProfile receiver = tx.getReceiverProfile();
			if (dto.getCardId() == null) {
				throw new MissingCreditCardException();
			} else {
				if (BillfoldConstants.TRANSACTION_ACTION_ACCEPTED.equals(dto.getPaymentAction())) {
					String phoneNumber = tx.getPayerProfile().getPhoneNumber();
					Float payment = tx.getAmount();
				
					
						Charge c;

						c = stripeUtil.transferFunds(tx, dto, payer, receiver,
								useProduction);

						if (c != null) {
							tx.setChargeId(c.getId());
						}
						tx.setStatus(BillfoldConstants.TRANSACTION_CLOSED);
				
					
				} else {
					tx.setStatus(BillfoldConstants.TRANSACTION_ACTION_REJECTED);
				}
			}
			tx = txRepo.save(tx);
		} catch (CardException ce) {
			// TODO Auto-generated catch block
			String errorCode = ce.getCode();
			if (errorCode != null) {
				switch (errorCode) {
				case "insufficient_funds":
				
					throw new InsufficientFundException();
				case "account_closed":
				
					throw new AccountClosedException();
				case "could_not_process":
				
					throw new BankProcessException();
				case "account_frozen":
				
					throw new AccountFrozenException();
				case "no_account":
				
					throw new IncorrectAccountDetailException();

				default:

					throw new BillFoldException();
				}

			}
		} catch (RuntimeException runEx) {
			throw runEx;
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new BillFoldException();
		}
		TransactionDTO txDTO = new TransactionDTO();
		txDTO.setId(tx.getId());
		txDTO.setStatus(tx.getStatus());
		return txDTO;
	}

}
