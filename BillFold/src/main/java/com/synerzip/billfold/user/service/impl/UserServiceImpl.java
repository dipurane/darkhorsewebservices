package com.synerzip.billfold.user.service.impl;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synerzip.billfold.stripe.entity.StripeAccount;
import com.synerzip.billfold.stripe.util.StripeUtil;
import com.synerzip.billfold.user.dao.repo.PhoneVerificationCodeRepository;
import com.synerzip.billfold.user.dao.repo.UserProfileRepository;
import com.synerzip.billfold.user.dto.UserProfileDTO;
import com.synerzip.billfold.user.dto.VerificationCodeDTO;
import com.synerzip.billfold.user.entity.LinkPhoneVerificationCode;
import com.synerzip.billfold.user.entity.UserProfile;
import com.synerzip.billfold.user.service.UserService;
import com.synerzip.billfold.util.SMSUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Component("userService")
public class UserServiceImpl implements UserService {

	/** The user profile repo. */
	@Autowired
	private UserProfileRepository userProfileRepo;

	/** The sms util. */
	@Autowired
	private SMSUtil smsUtil;

	/** The phoneverification repo. */
	@Autowired
	private PhoneVerificationCodeRepository phoneverificationRepo;

	@Autowired
	private StripeUtil stripeUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synerzip.billfold.user.service.UserService#saveOrUpdateUserProfile
	 * (com.synerzip.billfold.user.dto.UserProfileDTO)
	 */
	@Override
	@Transactional
	public UserProfileDTO saveOrUpdateUserProfile(UserProfileDTO dto) {
		// TODO Auto-generated method stub
		UserProfile profile = new UserProfile();
		BeanUtils.copyProperties(dto, profile);
		profile = userProfileRepo.save(profile);
		try {
			StripeAccount account = new StripeAccount();
			String stripeAccId = stripeUtil.createStripeCustomer(profile);
			account.setStripeAccountId(stripeAccId);
			account.setUserInfo(profile);
			if (profile.getStripeAccount() == null) {
				profile.setStripeAccount(new HashSet<StripeAccount>());
			}
			profile.getStripeAccount().add(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		profile = userProfileRepo.save(profile);
		
		dto.setId(profile.getId());
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synerzip.billfold.user.service.UserService#sendVerificationCode(com
	 * .synerzip.billfold.user.dto.VerificationCodeDTO)
	 */
	@Override
	@Transactional
	public Boolean sendVerificationCode(VerificationCodeDTO vdto) {
		// TODO Auto-generated method stub
		try {
			LinkPhoneVerificationCode code = new LinkPhoneVerificationCode();
			code.setPhoneNumber(vdto.getMobileNumber());
			String smscode = smsUtil.sendActivationCodeSMS(vdto
					.getMobileNumber());
			code.setVerificationCode(smscode);
			LinkPhoneVerificationCode linkcode = phoneverificationRepo
					.findByPhoneNumber(vdto.getMobileNumber());
			if (linkcode == null) {
				linkcode = new LinkPhoneVerificationCode();
				linkcode.setPhoneNumber(vdto.getMobileNumber());
				linkcode.setVerificationCode(smscode);
			} else {
				linkcode.setVerificationCode(smscode);
			}
			phoneverificationRepo.save(linkcode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.synerzip.billfold.user.service.UserService#verifyPhoneNumber(com.
	 * synerzip.billfold.user.dto.VerificationCodeDTO)
	 */
	@Override
	public Boolean verifyPhoneNumber(VerificationCodeDTO vdto) {
		// TODO Auto-generated method stub
		LinkPhoneVerificationCode linkcode = phoneverificationRepo
				.findByPhoneNumber(vdto.getMobileNumber());
		if (linkcode != null) {
			if (linkcode.getVerificationCode().equals(
					vdto.getVerificationCode()))
				return true;
		}
		return false;
	}

}
