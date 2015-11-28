package com.synerzip.billfold.stripe.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.synerzip.billfold.user.entity.UserProfile;

@Entity
@Table(name="link_user_stripe_account")
public class StripeAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_LINK_STRIPE_ACC_SEQ")
	@SequenceGenerator(allocationSize = 1, name = "GEN_LINK_STRIPE_ACC_SEQ", sequenceName = "link_user_stripe_account_sequence")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "stripe_acc_id")
	private String stripeAccountId;
	
	@OneToMany(mappedBy="stripeAccount",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserCreditCard> cardList;

	@ManyToOne
	@JoinColumn(name="user_id2tx_user_profile")
	private UserProfile userInfo;

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
	 * @return the cardList
	 */
	public Set<UserCreditCard> getCardList() {
		return cardList;
	}

	/**
	 * @param cardList the cardList to set
	 */
	public void setCardList(Set<UserCreditCard> cardList) {
		this.cardList = cardList;
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
