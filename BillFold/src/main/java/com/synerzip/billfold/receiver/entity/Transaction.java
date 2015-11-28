package com.synerzip.billfold.receiver.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.TransactionStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.synerzip.billfold.user.entity.UserProfile;
import com.wordnik.swagger.annotations.ApiModelProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaction.
 */
@Entity
@Table(name = "tx_transaction")
public class Transaction {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_TRANSACTION_ID")
	@SequenceGenerator(allocationSize = 1, name = "GEN_TRANSACTION_ID", sequenceName = "tx_transaction_seq")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	/** The receiver profile. */
	@ManyToOne
    @JoinColumn(name = "receiverId")
	@ApiModelProperty(dataType="UserProfile")
	private UserProfile receiverProfile;
	
	

	/** The payer profile. */
	@ManyToOne
    @JoinColumn(name = "payerId")
	@JsonProperty(value="payer")
	private UserProfile payerProfile;
	
	/** The amount. */
	@Column(name = "amount")
	private Float amount;
	

	/** The description. */
	@Column(name = "description")
	private String description;

	
	
	/** The status. */
	@NotNull
	@Column(name = "status")
	private String status;
	

	
	/** The charge id. */
	@Column(name = "charge_id")
	private String chargeId;
	
	
	/** The refund id. */
	@Column(name ="refund_id")
	private String refundId;

	/** The created date. */
	@NotNull
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name ="created_by")
	private Long createdBy;
	

	@Column(name ="updated_by")
	private Long updatedBy;
	
	
	
	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public Long getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	 * Gets the receiver profile.
	 *
	 * @return the receiver profile
	 */
	public UserProfile getReceiverProfile() {
		return receiverProfile;
	}


	/**
	 * Sets the receiver profile.
	 *
	 * @param receiverProfile the new receiver profile
	 */
	public void setReceiverProfile(UserProfile receiverProfile) {
		this.receiverProfile = receiverProfile;
	}


	/**
	 * Gets the payer profile.
	 *
	 * @return the payer profile
	 */
	public UserProfile getPayerProfile() {
		return payerProfile;
	}


	/**
	 * Sets the payer profile.
	 *
	 * @param payerProfile the new payer profile
	 */
	public void setPayerProfile(UserProfile payerProfile) {
		this.payerProfile = payerProfile;
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
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}


	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	 * Gets the charge id.
	 *
	 * @return the charge id
	 */
	public String getChargeId() {
		return chargeId;
	}


	/**
	 * Sets the charge id.
	 *
	 * @param chargeId the new charge id
	 */
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}


	/**
	 * Gets the refund id.
	 *
	 * @return the refund id
	 */
	public String getRefundId() {
		return refundId;
	}


	/**
	 * Sets the refund id.
	 *
	 * @param refundId the new refund id
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	
   	
	

}
