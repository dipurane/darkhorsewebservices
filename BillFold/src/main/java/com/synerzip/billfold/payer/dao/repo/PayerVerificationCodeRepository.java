package com.synerzip.billfold.payer.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synerzip.billfold.payer.entity.PayerVerificationCode;
import com.synerzip.billfold.user.entity.UserProfile;

public interface PayerVerificationCodeRepository extends JpaRepository<PayerVerificationCode, Long> {

	public PayerVerificationCode findByPayer(UserProfile profile);
}
