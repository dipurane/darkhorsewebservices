package com.synerzip.billfold.stripe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.synerzip.billfold.user.entity.UserProfile;

@Entity
@Table(name = "link_user_bank_account")
public class LinkUserBankAccounts {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LINK_BANK_ACC_USER_SEQ")
	@SequenceGenerator(allocationSize = 1, name = "GEN_LINK_BANK_ACC_USER_SEQ", sequenceName = "link_user_bank_acc_sequence")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name="account_number")
	private String bankAccountNumber;

	@Column(name="bank_name")
	private String bankName;
	

	@Column(name="last4_digits")
	private String lastFourDigits;
	
	@ManyToOne
    @JoinColumn(name = "user_id2tx_user_info")
	private UserProfile userInfo;
	
	@Column(name = "stripe_account_id")
	private String stripeAccountId;
	
	

	/**
	 * @return the stripeAccountId
	 */
	public String getStripeAccountId() {
		return stripeAccountId;
	}

	/**
	 * @param stripeAccountId the stripeAccountId to set
	 */
	public void setStripeAccountId(String stripeAccountId) {
		this.stripeAccountId = stripeAccountId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the bankAccountNumber
	 */
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	/**
	 * @param bankAccountNumber the bankAccountNumber to set
	 */
	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the lastFourDigits
	 */
	public String getLastFourDigits() {
		return lastFourDigits;
	}

	/**
	 * @param lastFourDigits the lastFourDigits to set
	 */
	public void setLastFourDigits(String lastFourDigits) {
		this.lastFourDigits = lastFourDigits;
	}

	/**
	 * @return the userInfo
	 */
	public UserProfile getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserProfile userInfo) {
		this.userInfo = userInfo;
	}
	
	
}
