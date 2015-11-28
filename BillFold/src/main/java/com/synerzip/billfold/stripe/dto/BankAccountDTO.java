package com.synerzip.billfold.stripe.dto;




//TODO: Auto-generated Javadoc
/**
* The Class BankAccountDTO.
*
* @author rohitghatol
*/
public class BankAccountDTO {

	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The email address. */
	private String emailAddress;
	
	/** The routing number. */
	private String routingNumber;
	
	/** The account number. */
	private String accountNumber;
	
	/** The bank name. */
	private String bankName;
	
	/** The tos acceptance date. */
	private String tosAcceptanceDate;
	
	/** The tos acceptance ip. */
	private String tosAcceptanceIp;
	
	/** The address. */
	private AddressDTO address;

	//FIXME make it enum
	/** The account type. */
	private String accountType;
	
	/** The birth date. */
	private DateDTO birthDate;
	
	
	/** The stripe account id. */
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
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public DateDTO getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate
	 *            the new birth date
	 */
	public void setBirthDate(DateDTO birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * Sets the account type.
	 *
	 * @param accountType
	 *            the new account type
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress
	 *            the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the routing number.
	 *
	 * @return the routing number
	 */
	public String getRoutingNumber() {
		return routingNumber;
	}

	/**
	 * Sets the routing number.
	 *
	 * @param routingNumber
	 *            the new routing number
	 */
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}

	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the account number.
	 *
	 * @param accountNumber
	 *            the new account number
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Gets the bank name.
	 *
	 * @return the bank name
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * Sets the bank name.
	 *
	 * @param bankName
	 *            the new bank name
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * Gets the tos acceptance date.
	 *
	 * @return the tos acceptance date
	 */
	public String getTosAcceptanceDate() {
		return tosAcceptanceDate;
	}

	/**
	 * Sets the tos acceptance date.
	 *
	 * @param tosAcceptanceDate
	 *            the new tos acceptance date
	 */
	public void setTosAcceptanceDate(String tosAcceptanceDate) {
		this.tosAcceptanceDate = tosAcceptanceDate;
	}

	/**
	 * Gets the tos acceptance ip.
	 *
	 * @return the tos acceptance ip
	 */
	public String getTosAcceptanceIp() {
		return tosAcceptanceIp;
	}

	/**
	 * Sets the tos acceptance ip.
	 *
	 * @param tosAcceptanceIp
	 *            the new tos acceptance ip
	 */
	public void setTosAcceptanceIp(String tosAcceptanceIp) {
		this.tosAcceptanceIp = tosAcceptanceIp;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public AddressDTO getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the new address
	 */
	public void setAddress(AddressDTO address) {
		this.address = address;
	}
	//add Object class methods necessary ones
	
	
}