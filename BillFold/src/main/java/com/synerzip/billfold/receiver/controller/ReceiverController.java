package com.synerzip.billfold.receiver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.synerzip.billfold.receiver.dto.TransactionDTO;
import com.synerzip.billfold.receiver.service.ReceiverService;
import com.synerzip.billfold.user.dto.UserProfileDTO;
import com.synerzip.billfold.user.exception.UserCreationException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(basePath = "/apis/receiver/{userId}", value = "Receiver Management", description = "Receiver Management APIs", produces = "application/json", position=1)
@RequestMapping(value = "/apis/receiver", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReceiverController {

	@Autowired
	private ReceiverService receiverService;
	
	 @RequestMapping(value="transaction",method = RequestMethod.POST, headers = "Accept=application/json")
	    @ResponseStatus(HttpStatus.OK)
	    @ApiOperation(value = "Create Transaction to raise invoice", notes = "Create Transaction to raise invoice")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "SUCCESS"),
				@ApiResponse(code = 406, message = "User Profile Can not be created due to server error"),
				@ApiResponse(code = 409, message = "Payer already have open transactions")
			 })
	    public TransactionDTO saveUserProfile(@PathVariable Long userId,@RequestBody TransactionDTO txDTO) {
	      //   empService.saveEmployee(e);
	    	try{
	    		
	    		txDTO = receiverService.createTransaction(txDTO, userId);
	    		return txDTO;
	    		
	    	}catch(Exception e){
	    		e.printStackTrace();
				throw new UserCreationException("Unable to create User Profile");
	    	}   
	    }
	
}