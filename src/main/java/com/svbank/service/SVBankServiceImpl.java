package com.svbank.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.svbank.entity.UserAccountBalance;
import com.svbank.entity.UserAccounts;
import com.svbank.entity.UserDetails;
import com.svbank.entity.UserMoneyTransfer;
import com.svbank.entity.UserSecurity;
import com.svbank.bean.UserDetailsBean;
import com.svbank.dao.SVBankDao;

public class SVBankServiceImpl implements SVBankService {

	
	@Autowired
    private SVBankDao svbankDAO;
	
	public void setSVBankDAO(SVBankDao svbankDAO) {
		this.svbankDAO = svbankDAO;
	}
	
	@Transactional
	public int updatePassword(UserDetailsBean userDetails, boolean setSecurity) {
		return svbankDAO.updatePassword(userDetails, setSecurity);
	}
	
	@Transactional
	public UserAccounts getUserAccounts(String userId){
		return svbankDAO.getUserAccounts(userId);
	}
	
	@Transactional
	public UserAccountBalance getUserAccountBalance(String userId){
		return svbankDAO.getUserAccountBalance(userId);
	}
	
	@Transactional
	 public int registerUser(UserDetailsBean userDetails) {
	  return svbankDAO.registerUser(userDetails);
	 }
	
	@Transactional
	public UserDetails getUserDetails(String userId){
		return svbankDAO.getUserDetails(userId);
	}
	
	@Transactional
	public UserSecurity getUseSecurity(String userId){
		return svbankDAO.getUseSecurity(userId);
	}
	
	@Transactional
	public Map<Integer,String> getSecurityQuestions(){
		return svbankDAO.getSecurityQuestions();
	}
	
	@Transactional
	public boolean isDefaultPwd(String userId){
		return svbankDAO.isDefaultPwd(userId);
	}

	@Transactional
	public Map<String, String> getUserAccountsAsMap(String userId) {
		return svbankDAO.getUserAccountsAsMap(userId);
	}
	
	public int mailingService(String event, String receiverEmail, UserDetailsBean userdetails) {
		return svbankDAO.mailingService(event, receiverEmail, userdetails);
	}
	
	@Transactional
	public String getSecurityQuestionById(int id) {
		return svbankDAO.getSecurityQuestionById(id);
	}
	
	@Transactional
	 public int updateUserDetails(UserDetails userDetails) {
	   return svbankDAO.updateUserDetails(userDetails);
	 }
	
	@Transactional
	public int setPin(UserDetailsBean pinDetails) {
		return svbankDAO.setPin(pinDetails);
	}

}
