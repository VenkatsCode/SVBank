package com.svbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.svbank.dao.AdminDao;
import com.svbank.entity.AccountRequests;
import com.svbank.entity.UserMoneyDeposit;

public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Transactional
	public List<AccountRequests> getAccountRequests() {
		return adminDao.getAccountRequests();
	}
	
	@Transactional
	 public int approveRejectRequests(AccountRequests statusChgRequest){
	  return adminDao.approveRejectRequests(statusChgRequest);
	 }
	
	@Transactional
	  public List<UserMoneyDeposit> getDepositRequests(){
	   return adminDao.getDepositRequests();
	  }
	 
	 @Transactional
	 public UserMoneyDeposit getChequeForTID(String transactionId){
	   return adminDao.getChequeForTID(transactionId);
	  }

}
