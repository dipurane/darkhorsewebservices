package com.synerzip.billfold.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.synerzip.billfold.user.dto.UserProfileDTO;
import com.synerzip.billfold.user.dto.VerificationCodeDTO;
import com.synerzip.billfold.user.exception.UserCreationException;
import com.synerzip.billfold.user.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(basePath = "/apis/usermanagement", value = "User Management", description = "User Management APIs", produces = "application/json", position=1)
@RequestMapping(value = "/apis/usermanagement", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;
	
	 @RequestMapping(value="userprofile",method = RequestMethod.POST, headers = "Accept=application/json")
	    @ResponseStatus(HttpStatus.OK)
	    @ApiOperation(value = "Save user profile Information", notes = "Save User profile Information")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "SUCCESS"),
				@ApiResponse(code = 406, message = "User Profile Can not be created due to server error")
			 })
	    public UserProfileDTO saveUserProfile(@RequestBody UserProfileDTO userDTO) {
	      //   empService.saveEmployee(e);
	    	try{
	    	userDTO = userService.saveOrUpdateUserProfile(userDTO);
	    	return userDTO;
	       	}catch(Exception e){
	    		e.printStackTrace();
				throw new UserCreationException("Unable to create User Profile");
	    	}   
	    }
	 
	 @RequestMapping(value="authentication",method = RequestMethod.POST, headers = "Accept=application/json")
	    @ResponseStatus(HttpStatus.OK)
	    @ApiOperation(value = "Send verification code for given number", notes = "Send verification code for given number")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "SUCCESS"),
				@ApiResponse(code = 406, message = "Can't send verification code")
			 })
	    public Boolean sendVerificationCode(@RequestBody VerificationCodeDTO vdto) {
	      //   empService.saveEmployee(e);
	    	try{
	    	
	    	return userService.sendVerificationCode(vdto);
	    
	    	}catch(Exception e){
	    		e.printStackTrace();
				throw new UserCreationException("Unable to Send verification code");
	    	}
	     }
	 
	 @RequestMapping(value="verification",method = RequestMethod.POST, headers = "Accept=application/json")
	    @ResponseStatus(HttpStatus.OK)
	    @ApiOperation(value = "Send verification code for given number", notes = "Send verification code for given number")
		@ApiResponses(value = {
				@ApiResponse(code = 200, message = "SUCCESS"),
				@ApiResponse(code = 406, message = "Can't send verification code")
			 })
	    public Boolean verifyUser(@RequestBody VerificationCodeDTO vdto) {
	      //   empService.saveEmployee(e);
	    	try{
	    	
	    		return userService.verifyPhoneNumber(vdto);
	    
	    	}catch(Exception e){
	    		e.printStackTrace();
				throw new UserCreationException("Unable to Send verification code");
	    	}
	      
	    }
	 
}
