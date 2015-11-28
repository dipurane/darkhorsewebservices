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


@Entity
@Table(name = "link_user_card")
public class UserCreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LINK_USER_CREDIT_CARD_SEQ")
	@SequenceGenerator(allocationSize = 1, name = "GEN_LINK_USER_CREDIT_CARD_SEQ", sequenceName = "link_user_credit_card_sequence")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	//FIXME addd enum
	
	/** The payment gateway customer account. */
	@ManyToOne
	@JoinColumn(name="stripe_id2stripe_account")
	private StripeAccount stripeAccount;
	
	/** The default card. */
	@Column(name = "is_default_card")
	private boolean defaultCard;
	
	/** The card type. */
	@Column(name = "card_type")
	private String cardType;
	
	/** The last four digits. */
	@Column(name = "last_4_digits")
	private String lastFourDigits;
	
	/** The card id. */
	@Column(name = "card_id")
	private String cardId;
	
	/** The token id. */
	@Column(name = "token_id")
	private String tokenId;

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
	 * @return the stripeAccount
	 */
	public StripeAccount getStripeAccount() {
		return stripeAccount;
	}

	/**
	 * @param stripeAccount the stripeAccount to set
	 */
	public void setStripeAccount(StripeAccount stripeAccount) {
		this.stripeAccount = stripeAccount;
	}

	/**
	 * @return the defaultCard
	 */
	public boolean isDefaultCard() {
		return defaultCard;
	}

	/**
	 * @param defaultCard the defaultCard to set
	 */
	public void setDefaultCard(boolean defaultCard) {
		this.defaultCard = defaultCard;
	}

	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}

	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	 * @return the cardId
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * @param cardId the cardId to set
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	
}
