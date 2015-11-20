package com.svbank.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.svbank.bean.UserDetailsBean;
import com.svbank.dao.TransactionDao;
import com.svbank.entity.UserMoneyDeposit;
import com.svbank.entity.UserMoneyRecipients;
import com.svbank.entity.UserMoneyTransfer;
import com.svbank.entity.UserMoneyWithdraw;

public class TransactionServiceImpl implements TransactionService {

	@Autowired
	public TransactionDao transactionDAO;
	
	@Transactional
	public int transferMoney(UserMoneyTransfer userMoneyTransfer, String transferType) {
		return transactionDAO.transferMoney(userMoneyTransfer, transferType);
	}
	
	@Transactional
	public int addRecipient(UserMoneyRecipients userMoneyRecipients) {
		return transactionDAO.addRecipient(userMoneyRecipients);
	}
	
	@Transactional
	public Map<String, String> getRecipientsAsMap(String userId) {
		return transactionDAO.getRecipientsAsMap(userId);
	}
	
	@Transactional
	public List<UserMoneyTransfer> getRequests(String userId) {
		return transactionDAO.getRequests(userId);
	}
	
	@Transactional
	public int updateTransferRequests(String transactionId, UserDetailsBean userdetails) {
		return transactionDAO.updateTransferRequests(transactionId, userdetails);
	}
	
	@Transactional
	 public String withdrawMoney(UserMoneyWithdraw userMoneyWithdraw) {
	  return transactionDAO.withdrawMoney(userMoneyWithdraw);
	 }
	 
	 @Transactional
	 public List<UserMoneyDeposit> getDepositHistoryForUser(String userId){
	  return transactionDAO.getDepositHistoryForUser(userId);
	 }
	 
	 @Transactional
	 public int depositMoney(UserMoneyDeposit userMoneyDeposit) {
	  return transactionDAO.depositMoney(userMoneyDeposit);
	 }
}
