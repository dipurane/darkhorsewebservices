package com.synerzip.billfold.stripe.dto;


public class CreditCardDTO {

	private Long id;
	
	private boolean defaultCard;
	
	private String cardType;
	
	private String lastFourDigits;

	private String cardId;

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
