package com.synerzip.billfold.payer.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.synerzip.billfold.payer.dto.PaymentActionDTO;
import com.synerzip.billfold.payer.service.PayerService;
import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.user.exception.UserCreationException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(basePath = "/apis/payer/{userId}", value = "Payer Management", description = "Payer Management APIs", produces = "application/json", position = 2)
@RequestMapping(value = "/apis/payer/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
public class PayerController {

	@Autowired
	private PayerService payerService;

	@RequestMapping(value = "pvc", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Generate PVC to raise invoice", notes = "Generate PVC to raise invoice")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 406, message = "Can not generate PVC due to server error"), })
	public String saveUserProfile(@PathVariable("userId") Long userId) {
		// empService.saveEmployee(e);
		try {
			return payerService.generatePVCCode(userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserCreationException("Unable to generate PVC ");
		}
	}

	@RequestMapping(value = "transaction", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get current Transaction for user", notes = "Get current Transaction for user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "SUCCESS"),
			@ApiResponse(code = 404, message ="No current transction availble for user") })
	public TransactionDTO getCurrentTransaction(
			@PathVariable("userId") Long userId) {
		// empService.saveEmployee(e);

		return payerService.getOpenTransactionForPayer(userId);

	}
	
	
	@ApiOperation(value = "Update Transaction", response = TransactionDTO.class, httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Transaction updated successfully"),
			@ApiResponse(code = 400, message = "Payer exceed daily allowed payment limit"),
			@ApiResponse(code = 428, message = "Insufficient fund can't process transaction") })
	@RequestMapping(value = "transactions/{transactionId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	@PreAuthorize("hasAnyRole('ROLE_USER') and @paywizeAuthorize.isValid(#userId,authentication.name) == true")
	public TransactionDTO updateTransaction(
			@ApiParam(name = "userId", value = "Unique Id of Paywize User", required = true) @PathVariable("userId") Long userId,
			@ApiParam(name = "transactionId", value = "Unique Transaction Id", required = true) @PathVariable("transactionId") Long transactionId,
			@RequestBody PaymentActionDTO paymentAction) {
	
		return payerService.processTransaction(transactionId, paymentAction);

	}
	
	

}
