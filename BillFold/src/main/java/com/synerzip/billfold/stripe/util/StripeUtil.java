package com.synerzip.billfold.stripe.util;

/**
 * @author Dipesh
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Account;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.synerzip.billfold.payer.dto.PaymentActionDTO;
import com.synerzip.billfold.receiver.entity.Transaction;
import com.synerzip.billfold.stripe.dto.BankAccountDTO;
import com.synerzip.billfold.stripe.entity.StripeAccount;
import com.synerzip.billfold.stripe.entity.UserCreditCard;
import com.synerzip.billfold.user.entity.UserProfile;

// TODO: Auto-generated Javadoc
/**
 * The Class StripeUtil.
 */
@Component
public class StripeUtil {

	/** The stripe test key. */
	@Value("${soceana.stripe.testKey}")
	private String stripeTestKey;

	/** The stripe live key. */
	@Value("${soceana.stripe.liveKey}")
	private String stripeLiveKey;

	/** The stripe mode. */
	@Value("${soceana.stripe.productionmode}")
	private Boolean stripeMode;

	/**
	 * Creates the bank account.
	 *
	 * @param dto the dto
	 * @return the string
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public String createBankAccount(BankAccountDTO dto)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {

		if (stripeMode) {
			Stripe.apiKey = stripeLiveKey;
		} else {
			Stripe.apiKey = stripeTestKey;
		}

		Map<String, Object> accountParams = new HashMap<String, Object>();
		accountParams.put("managed", true);
		accountParams.put("country", "US");
		accountParams.put("email", dto.getEmailAddress());

		Map<String, Object> legalMap = new HashMap<String, Object>();
		legalMap.put("first_name", dto.getFirstName());
		legalMap.put("last_name", dto.getLastName());
		legalMap.put("type", dto.getAccountType());

		Map<String, Object> addressMap = new HashMap<String, Object>();
		addressMap.put("line1", dto.getAddress().getLine1());
		addressMap.put("line2", dto.getAddress().getLine2());
		addressMap.put("city", dto.getAddress().getCity());
		addressMap.put("state", dto.getAddress().getState());
		addressMap.put("postal_code", dto.getAddress().getPostalCode());
		addressMap.put("country", dto.getAddress().getCountry());

		Map<String, Object> bankMap = new HashMap<String, Object>();
		bankMap.put("country", dto.getAddress().getCountry());
		bankMap.put("currency", "USD");
		bankMap.put("routing_number", dto.getRoutingNumber());
		bankMap.put("account_number", dto.getAccountNumber());
		Map<String, Object> tosMap = new HashMap<String, Object>();

		tosMap.put("date", System.currentTimeMillis() / 1000);
		// currently use 115.111.59.243
		tosMap.put("ip", dto.getTosAcceptanceIp());

		Map<String, Object> dobMap = new HashMap<String, Object>();
		dobMap.put("day", dto.getBirthDate().getDay());
		dobMap.put("month", dto.getBirthDate().getMonth());
		dobMap.put("year", dto.getBirthDate().getYear());
		legalMap.put("dob", dobMap);

		// add address to leagal
		legalMap.put("address", addressMap);

		accountParams.put("tos_acceptance", tosMap);
		accountParams.put("bank_account", bankMap);

		accountParams.put("legal_entity", legalMap);

		Account acc = Account.create(accountParams);
		return acc.getId();

	}

	/**
	 * Creates the stripe customer.
	 *
	 * @param info the info
	 * @return the string
	 * @throws Exception the exception
	 */
	public String createStripeCustomer(UserProfile info) throws Exception {
		if (stripeMode) {
			Stripe.apiKey = stripeLiveKey;
		} else {
			Stripe.apiKey = stripeTestKey;
		}
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("email", info.getEmail());
		customerParams.put("description", info.getPhoneNumber());
		Customer customer = Customer.create(customerParams);
		return customer.getId();
	}

	/**
	 * Link card for stripe customer.
	 *
	 * @param info the info
	 * @param card the card
	 * @return the user credit card
	 * @throws Exception the exception
	 */
	public synchronized UserCreditCard linkCardForStripeCustomer(
			UserProfile info, UserCreditCard card) throws Exception {

		if (stripeMode) {
			Stripe.apiKey = stripeLiveKey;
		} else {
			Stripe.apiKey = stripeTestKey;
		}
		List<StripeAccount> accList = new ArrayList<StripeAccount>(
				info.getStripeAccount());
		StripeAccount stripeAccount = accList.get(0);
		Customer cu = Customer.retrieve(stripeAccount.getStripeAccountId());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("source", card.getTokenId());
		com.stripe.model.Card c = cu.createCard(params);
		card.setCardId(c.getId());
		card.setCardType(c.getType());
		card.setLastFourDigits(c.getLast4());
		return card;
	}


	public  Charge transferFunds(Transaction tx, PaymentActionDTO dto,
			UserProfile payer, UserProfile receiver, Boolean useProduction)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException {
	
		if(useProduction){
			Stripe.apiKey  = stripeLiveKey;
		}else{
			Stripe.apiKey  = stripeTestKey;
		}
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		Double amountInCents = new Double((tx.getAmount() * 100));

		chargeParams.put("amount", amountInCents.intValue());
		chargeParams.put("currency", "usd");
		chargeParams.put("customer", payer.getStripeAccount().iterator().next().getStripeAccountId());
		chargeParams.put("source", dto.getCardId());
		chargeParams.put(
				"description",
				"Charge for " + receiver.getPhoneNumber() + " From "
						+ payer.getPhoneNumber());
		chargeParams.put("destination", receiver.getBankAccounts().iterator().next().getStripeAccountId());
		Charge c;
		c = Charge.create(chargeParams);
		return c;

	}
}
