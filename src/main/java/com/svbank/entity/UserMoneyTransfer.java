package com.svbank.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "UserMoneyTransfer")
@Table(name="USER_MONEY_TRANSFER")
public class UserMoneyTransfer {

    @Column(name="USER_ID")
    private String userId;
	
    @Id
	@Column(name="TRANSACTION_ID")
    private String transactionId;
	
	@Column(name="FROM_ACC")
    private String fromAccount;
	
	@Column(name="TO_ACC")
    private String toAccount;
	
	@Column(name="STATUS")
    private String status;
	
	@Column(name="DATE_CREATED")
    private Date dateCreated;
	
	@Column(name="DATE_APPROVED")
    private Date dateApproved;
	
	@Column(name="AMOUNT")
    private String amount;
	
	@Column(name="BALANCE")
    private String balance;

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

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateApproved() {
		return dateApproved;
	}

	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

}
