package com.svbank.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserMoneyWithdraw")
@Table(name="USER_MONEY_WITHDRAW")
public class UserMoneyWithdraw {

    @Column(name="USER_ID")
    private String userId;
	
    @Id
	@Column(name="TRANSACTION_ID")
    private String transactionId;
	
	@Column(name="FROM_ACC")
    private String fromAccount;
	
	@Column(name="DATE_TRANSFERRED")
    private Date dateTransferred;
	
	@Column(name="AMOUNT")
    private String amount;
	
	@Column(name="MONEY_CARDNO")
    private String moneyCardNo;

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Date getDateTransferred() {
		return dateTransferred;
	}

	public void setDateTransferred(Date dateTransferred) {
		this.dateTransferred = dateTransferred;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMoneyCardNo() {
		return moneyCardNo;
	}

	public void setMoneyCardNo(String moneyCardNo) {
		this.moneyCardNo = moneyCardNo;
	}

}
