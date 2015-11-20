package com.svbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserAccounts")
@Table(name="USER_ACCOUNTS")
public class UserAccounts {
     
    @Id
    @Column(name="USER_ID")
    private String userId;
     
    @Column(name="CHQ_ACC")
    private String chqAcc;
    
	@Column(name="SAV_ACC")
    private String savAcc;
    
    @Column(name="CREDIT_ACC")
    private String creditAcc;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChqAcc() {
		return chqAcc;
	}

	public void setChqAcc(String chqAcc) {
		this.chqAcc = chqAcc;
	}

	public String getSavAcc() {
		return savAcc;
	}

	public void setSavAcc(String savAcc) {
		this.savAcc = savAcc;
	}

	public String getCreditAcc() {
		return creditAcc;
	}

	public void setCreditAcc(String creditAcc) {
		this.creditAcc = creditAcc;
	}

 
}