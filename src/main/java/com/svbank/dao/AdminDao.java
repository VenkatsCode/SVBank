package com.svbank.dao;

import java.util.List;

import com.svbank.entity.AccountRequests;
import com.svbank.entity.UserMoneyDeposit;

public interface AdminDao {
	
	public List<AccountRequests> getAccountRequests();
	public int approveRejectRequests(AccountRequests statusChgRequest);
	public List<UserMoneyDeposit> getDepositRequests();
	public UserMoneyDeposit getChequeForTID(String transactionId);

}
