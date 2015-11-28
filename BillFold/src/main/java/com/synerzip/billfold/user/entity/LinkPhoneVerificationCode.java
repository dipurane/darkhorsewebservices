package com.synerzip.billfold.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkPhoneVerificationCode.
 */
@Entity
@Table(name ="link_phone_verification_code")
public class LinkPhoneVerificationCode {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LINK_MOBILE_CODE")
	@SequenceGenerator(allocationSize = 1, name = "GEN_LINK_MOBILE_CODE", sequenceName = "link_phone_verification_code_seq")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	/** The phone number. */
	@Column(name="phone_number")
	private String phoneNumber;
	
	/** The verification code. */

	@Column(name="verification_code")
	private String verificationCode;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the verification code.
	 *
	 * @return the verification code
	 */
	public String getVerificationCode() {
		return verificationCode;
	}

	/**
	 * Sets the verification code.
	 *
	 * @param verificationCode the new verification code
	 */
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	
}
