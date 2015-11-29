package com.synerzip.billfold.stripe.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.synerzip.billfold.stripe.dto.BankAccountDTO;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;
import com.synerzip.billfold.stripe.entity.LinkUserBankAccounts;
import com.synerzip.billfold.stripe.entity.StripeAccount;
import com.synerzip.billfold.stripe.entity.UserCreditCard;
import com.synerzip.billfold.stripe.service.StripeService;
import com.synerzip.billfold.stripe.util.StripeUtil;
import com.synerzip.billfold.user.dao.repo.UserProfileRepository;
import com.synerzip.billfold.user.entity.UserProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class StripeServiceImpl.
 */
@Component("stripeService")
public class StripeServiceImpl implements StripeService {

	/** The stripe util. */
	@Autowired
	private StripeUtil stripeUtil;

	/** The user repo. */
	@Autowired
	private UserProfileRepository userRepo;

	/* (non-Javadoc)
	 * @see com.synerzip.billfold.stripe.service.StripeService#registerBankAccount(com.synerzip.billfold.stripe.dto.BankAccountDTO, java.lang.Long)
	 */
	@Override
	@Transactional
	public BankAccountDTO registerBankAccount(BankAccountDTO dto, Long userId) {
		// TODO Auto-generated method stub
		try {
			UserProfile profile = userRepo.findById(userId);
			dto.setLastName(profile.getLastName());
			dto.setAccountType("sole_prop");
			dto.setTosAcceptanceIp("121.247.75.143");
			dto.setFirstName(profile.getFirstName());
			dto.setEmailAddress(profile.getEmail());
			
			
			String stripeAccId = stripeUtil.createBankAccount(dto);
			UserProfile info = userRepo.findById(userId);
			LinkUserBankAccounts account = new LinkUserBankAccounts();
			account.setUserInfo(info);
			account.setBankAccountNumber(stripeAccId);
			account.setBankName(dto.getBankName());
			account.setLastFourDigits(dto.getAccountNumber());
			account.setStripeAccountId(stripeAccId);
			info.getBankAccounts().add(account);
			userRepo.save(info);
			dto.setStripeAccountId(stripeAccId);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;
	}

	/* (non-Javadoc)
	 * @see com.synerzip.billfold.stripe.service.StripeService#registerCreditCard(com.synerzip.billfold.stripe.dto.CreditCardDTO, java.lang.Long)
	 */
	@Override
	public CreditCardDTO registerCreditCard(CreditCardDTO dto, Long userId) {
		try {
			UserProfile info = userRepo.findById(userId);
			List<StripeAccount> accList = new ArrayList<StripeAccount>(
					info.getStripeAccount());
			
			StripeAccount stripeAccount = accList.get(0);

			Set<UserCreditCard> cards = stripeAccount.getCardList();
			UserCreditCard card = new UserCreditCard();
			BeanUtils.copyProperties(dto, card);
			card.setStripeAccount(stripeAccount);
			if (cards == null) {
				cards = new HashSet<UserCreditCard>();
			}
			card = stripeUtil.linkCardForStripeCustomer(info, card);
			card.setLastFourDigits(dto.getLastFourDigits());
		//	stripeAccount.getCardList().clear();
			stripeAccount.getCardList().add(card);
			info.getStripeAccount().clear();
			info.getStripeAccount().add(stripeAccount);
			dto.setCardId(card.getCardId());
			userRepo.save(info);
		} catch (CardException ce) {

			/*
			 * String errorCode = ce.getCode(); if(errorCode != null){
			 * switch(errorCode){ case "card_declined" :
			 * log.error("Unable to process Transaction due to Insufficient funds"
			 * ,ce); throw new InsufficientFundException(); case
			 * "account_closed" : log.error(
			 * "Unable to process Transaction due to Bank account is closed"
			 * ,ce); throw new AccountClosedException(); case
			 * "could_not_process" : log.error(
			 * "Unable to process Transaction due to Bank couldnt process payments"
			 * ,ce); throw new BankProcessException(); case "account_frozen" :
			 * log
			 * .error("Unable to process Transaction due to Account Frozen",ce);
			 * throw new AccountFrozenException(); case "no_account" :
			 * log.error(
			 * "Unable to process Transaction due to account details are incorrect"
			 * ,ce); throw new IncorrectAccountDetailException();
			 * 
			 * default : log.error(
			 * "Unable to process Transaction due to internal error on Payment gateway"
			 * ,ce); throw new PaywizeException(); } }
			 */
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;

	}

}
