package com.synerzip.billfold.stripe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.synerzip.billfold.payer.service.PayerService;
import com.synerzip.billfold.stripe.dto.BankAccountDTO;
import com.synerzip.billfold.stripe.dto.CreditCardDTO;
import com.synerzip.billfold.stripe.service.StripeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

// TODO: Auto-generated Javadoc
/**
 * The Class PaymentGatewayController.
 */
@RestController
@Api(basePath = "/apis/paymentgateway/{userId}", value = "PaymentGatway", description = "Payment Gateway Integration APIs", produces = "application/json", position = 6)
@RequestMapping(value = "/apis/paymentgateway/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentGatewayController {

	/** The stripe service. */
	@Autowired
	private StripeService stripeService;

	/** The payer service. */
	@Autowired
	private PayerService payerService;

	/**
	 * Register bank account.
	 *
	 * @param userId the user id
	 * @param dto the dto
	 * @return the bank account dto
	 */
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "bankaccount")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Register Bank Account for User", notes = "Register Bank Account for User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Category Data Not Found"), })
	public BankAccountDTO registerBankAccount(@PathVariable Long userId,
			@RequestBody BankAccountDTO dto) {

		return stripeService.registerBankAccount(dto, userId);

	}

	/**
	 * Register credit card.
	 *
	 * @param userId the user id
	 * @param dto the dto
	 * @return the credit card dto
	 */
	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", value = "creditcard")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Register Credit Card for User", notes = "Register Credit Card for User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Category Data Not Found"), })
	public CreditCardDTO registerCreditCard(@PathVariable Long userId,
			@RequestBody CreditCardDTO dto) {

		return stripeService.registerCreditCard(dto, userId);

	}

	/**
	 * Gets the all credit cards.
	 *
	 * @param userId the user id
	 * @param dto the dto
	 * @return the all credit cards
	 */
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", value = "creditcard")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Fetch Credit Cards for User", notes = "Fetch Credit Cards for User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message = "Credit cards not found"), })
	public List<CreditCardDTO> getAllCreditCards(@PathVariable Long userId) {

		return payerService.getAllCardsForPayer(userId);

	}

}
