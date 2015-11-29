package com.synerzip.billfold.receiver.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class TransactionDTO.
 */
public class TransactionDTO {

	/** The id. */
	private Long id;
	
	/** The user id. */
	private  Long userId;
	
	/** The status. */
	private String status;
	
	/** The payer phone number. */
	private String payerPhoneNumber;
	
	/** The payer bvc code. */
	private String payerBVCCode;
	
	/** The description. */
	private String description;
	
	/** The amount. */
	private Float amount;
	
	
	private String createdDateStr;
	
	/**
	 * @return the createdDateStr
	 */
	public String getCreatedDateStr() {
		return createdDateStr;
	}

	/**
	 * @param createdDateStr the createdDateStr to set
	 */
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}

	/** The receiver phone number. */
	private String receiverPhoneNumber;
	
	
	/**
	 * Gets the receiver phone number.
	 *
	 * @return the receiverPhoneNumber
	 */
	public String getReceiverPhoneNumber() {
		return receiverPhoneNumber;
	}

	/**
	 * Sets the receiver phone number.
	 *
	 * @param receiverPhoneNumber the receiverPhoneNumber to set
	 */
	public void setReceiverPhoneNumber(String receiverPhoneNumber) {
		this.receiverPhoneNumber = receiverPhoneNumber;
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
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the payer phone number.
	 *
	 * @return the payer phone number
	 */
	public String getPayerPhoneNumber() {
		return payerPhoneNumber;
	}

	/**
	 * Sets the payer phone number.
	 *
	 * @param payerPhoneNumber the new payer phone number
	 */
	public void setPayerPhoneNumber(String payerPhoneNumber) {
		this.payerPhoneNumber = payerPhoneNumber;
	}

	/**
	 * Gets the payer bvc code.
	 *
	 * @return the payer bvc code
	 */
	public String getPayerBVCCode() {
		return payerBVCCode;
	}

	/**
	 * Sets the payer bvc code.
	 *
	 * @param payerBVCCode the new payer bvc code
	 */
	public void setPayerBVCCode(String payerBVCCode) {
		this.payerBVCCode = payerBVCCode;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	
	
}
