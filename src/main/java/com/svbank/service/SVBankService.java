package com.svbank.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.svbank.bean.UserDetailsBean;
import com.svbank.entity.UserAccountBalance;
import com.svbank.entity.UserAccounts;
import com.svbank.entity.UserDetails;
import com.svbank.entity.UserMoneyTransfer;
import com.svbank.entity.UserSecurity;

public interface SVBankService {
    public int updatePassword(UserDetailsBean userDetails, boolean setSecurity);
    public UserAccounts getUserAccounts(String userId);
    public Map<String, String> getUserAccountsAsMap(String userId);
    public UserAccountBalance getUserAccountBalance(String userId);
    public int registerUser(UserDetailsBean userDetails);
    public UserDetails getUserDetails(String userId);
    public UserSecurity getUseSecurity(String userId);
    public Map<Integer,String> getSecurityQuestions();
    public boolean isDefaultPwd(String userId);
    public int mailingService(String event, String receiverEmail, UserDetailsBean userdetails);
    public String getSecurityQuestionById(int id);
    public int updateUserDetails(UserDetails userDetails);
    public int setPin(UserDetailsBean pinDetails);
    
}
