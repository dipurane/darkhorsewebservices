package com.synerzip.billfold.user.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synerzip.billfold.user.entity.LinkPhoneVerificationCode;

public interface PhoneVerificationCodeRepository extends JpaRepository<LinkPhoneVerificationCode, Long>{

	public LinkPhoneVerificationCode findByPhoneNumber(String phoneNumber);  
}
