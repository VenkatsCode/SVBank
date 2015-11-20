package com.svbank.entity;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity (name = "UserMoneyDeposit")
@Table(name="USER_MONEY_DEPOSIT")
public class UserMoneyDeposit {

    @Column(name="USER_ID")
    private String userId;
	
    @Id
	@Column(name="TRANSACTION_ID")
    private String transactionId;
	
	@Column(name="To_ACC")
    private String toAccount;
	
	@Column(name="DATE_TRANSFERRED")
    private Date dateTransferred;
	
	@Column(name="AMOUNT")
    private String amount;
	
	@Column(name="CHEQUE_NO")
    private String chequeNo;

	@Column(name="STATUS")
    private String status;

	@Column(name="CHEQUE_IMG")
	@Lob
    private Blob chequeImg;

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

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
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

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Blob getChequeImg() {
		return chequeImg;
	}

	public void setChequeImg(Blob chequeImg) {
		this.chequeImg = chequeImg;
	}

}
