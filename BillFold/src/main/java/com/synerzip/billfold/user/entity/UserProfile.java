package com.synerzip.billfold.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.synerzip.billfold.stripe.entity.LinkUserBankAccounts;
import com.synerzip.billfold.stripe.entity.StripeAccount;

// TODO: Auto-generated Javadoc
/**
 * The Class UserProfile.
 */
@Entity
@Table(name = "tx_user_profile")
public class UserProfile {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_USER_INFO_SEQ")
	@SequenceGenerator(allocationSize = 1, name = "GEN_USER_INFO_SEQ", sequenceName = "tx_user_info_seq")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	/** The email. */
	@Column(name = "email")
	private String email;
	
	/** The first name. */
	@Column(name = "first_name")
	private String firstName;
	
	/** The last name. */
	@Column(name = "last_name")
	private String lastName;
	
	/** The phone number. */
	@Column(name = "phone_number")
	private String phoneNumber;
	
	/** The is verified. */
	@Column(name = "is_verified")
	private Boolean isVerified;
	
	/** The bank accounts. */
	@OneToMany(mappedBy="userInfo" , cascade = CascadeType.ALL, orphanRemoval = true)    
	private Set<LinkUserBankAccounts> bankAccounts;
	
	/** The stripe account. */
	@OneToMany(mappedBy="userInfo" , cascade = CascadeType.ALL, orphanRemoval = true)    
	private Set<StripeAccount> stripeAccount;

	
	
	/**
	 * Gets the bank accounts.
	 *
	 * @return the bank accounts
	 */
	public Set<LinkUserBankAccounts> getBankAccounts() {
		return bankAccounts;
	}

	/**
	 * Sets the bank accounts.
	 *
	 * @param bankAccounts the new bank accounts
	 */
	public void setBankAccounts(Set<LinkUserBankAccounts> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	/**
	 * Gets the stripe account.
	 *
	 * @return the stripe account
	 */
	public Set<StripeAccount> getStripeAccount() {
		return stripeAccount;
	}

	/**
	 * Sets the stripe account.
	 *
	 * @param stripeAccount the new stripe account
	 */
	public void setStripeAccount(Set<StripeAccount> stripeAccount) {
		this.stripeAccount = stripeAccount;
	}

	/**
	 * Gets the checks if is verified.
	 *
	 * @return the checks if is verified
	 */
	public Boolean getIsVerified() {
		return isVerified;
	}

	/**
	 * Sets the checks if is verified.
	 *
	 * @param isVerified the new checks if is verified
	 */
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
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
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProfile other = (UserProfile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
