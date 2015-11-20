package com.svbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserAccountBalance")
@Table(name="USER_ACCOUNT_BALANCE")
public class UserAccountBalance {
     
    @Id
    @Column(name="USER_ID")
    private String userId;
     
    @Column(name="CHQ_ACC_BALANCE")
    private String chqAccBalance;
    
	@Column(name="SAV_ACC_BALANCE")
    private String savAccBalance;
    
    @Column(name="CREDIT_ACC_BALANCE")
    private String creditAccBalance;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChqAccBalance() {
		return chqAccBalance;
	}

	public void setChqAccBalance(String chqAccBalance) {
		this.chqAccBalance = chqAccBalance;
	}

	public String getSavAccBalance() {
		return savAccBalance;
	}

	public void setSavAccBalance(String savAccBalance) {
		this.savAccBalance = savAccBalance;
	}

	public String getCreditAccBalance() {
		return creditAccBalance;
	}

	public void setCreditAccBalance(String creditAccBalance) {
		this.creditAccBalance = creditAccBalance;
	}
 
}