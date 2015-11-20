package com.svbank.service;

import java.util.List;
import java.util.Map;

import com.svbank.bean.UserDetailsBean;
import com.svbank.entity.UserMoneyDeposit;
import com.svbank.entity.UserMoneyRecipients;
import com.svbank.entity.UserMoneyTransfer;
import com.svbank.entity.UserMoneyWithdraw;

public interface TransactionService {

	public int transferMoney(UserMoneyTransfer userMoneyTransfer, String transferType);
	public int addRecipient(UserMoneyRecipients userMoneyRecipients);
	public Map<String, String> getRecipientsAsMap(String userId);
	public List<UserMoneyTransfer> getRequests(String userId);
	public int updateTransferRequests(String transactionId, UserDetailsBean userdetails);
	public String withdrawMoney(UserMoneyWithdraw userMoneyWithdraw);
	public List<UserMoneyDeposit> getDepositHistoryForUser(String userId);
	public int depositMoney(UserMoneyDeposit userMoneyDeposit);
}
