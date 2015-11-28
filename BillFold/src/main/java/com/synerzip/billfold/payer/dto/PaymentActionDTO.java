package com.synerzip.billfold.payer.dto;

public class PaymentActionDTO {

	private String cardId;
	
	private String paymentAction;

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
	 * @return the paymentAction
	 */
	public String getPaymentAction() {
		return paymentAction;
	}

	/**
	 * @param paymentAction the paymentAction to set
	 */
	public void setPaymentAction(String paymentAction) {
		this.paymentAction = paymentAction;
	}
	
	
}
