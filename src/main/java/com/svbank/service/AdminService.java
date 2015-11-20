package com.svbank.service;

import java.util.List;

import com.svbank.entity.AccountRequests;
import com.svbank.entity.UserMoneyDeposit;

public interface AdminService {
	
	public List<AccountRequests> getAccountRequests();
	public int approveRejectRequests(AccountRequests statusChgRequest);
	public List<UserMoneyDeposit> getDepositRequests();
	public UserMoneyDeposit getChequeForTID(String transactionId);
}
