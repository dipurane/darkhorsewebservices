package com.synerzip.billfold.payer.entity;

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
@Table(name ="tx_billfold_verification_code")
public class PayerVerificationCode {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_PVC_ID")
	@SequenceGenerator(allocationSize = 1, name = "GEN_PVC_ID", sequenceName = "tx_billfold_verification_code_sequence")
	@Column(name = "id", nullable = false, unique = true)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "payer_id")
	private UserProfile payer;
	
	@Column(name = "verification_code")
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public UserProfile getPayer() {
		return payer;
	}

	public void setPayer(UserProfile payer) {
		this.payer = payer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
