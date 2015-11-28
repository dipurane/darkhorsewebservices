package com.synerzip.billfold.user.service;

import com.synerzip.billfold.user.dto.UserProfileDTO;
import com.synerzip.billfold.user.dto.VerificationCodeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Save or update user profile.
	 *
	 * @param dto the dto
	 * @return the user profile dto
	 */
	public UserProfileDTO saveOrUpdateUserProfile(UserProfileDTO dto);
	
	/**
	 * Send verification code.
	 *
	 * @param vdto the vdto
	 * @return the boolean
	 */
	public Boolean sendVerificationCode(VerificationCodeDTO vdto);
	
	
	/**
	 * Verify phone number.
	 *
	 * @param dto the dto
	 * @return the boolean
	 */
	public Boolean verifyPhoneNumber(VerificationCodeDTO dto);
}
