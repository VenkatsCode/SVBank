package com.svbank.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.svbank.bean.UserDetailsBean;
import com.svbank.entity.UserAccountBalance;
import com.svbank.entity.UserAccounts;
import com.svbank.entity.UserDetails;
import com.svbank.entity.UserMoneyDeposit;
import com.svbank.entity.UserMoneyRecipients;
import com.svbank.entity.UserMoneyTransfer;
import com.svbank.entity.UserMoneyWithdraw;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private SVBankDao svBankDao;
	
	public int transferMoney(UserMoneyTransfer userMoneyTransfer, String transferType) {
		
		int result=0;
		Session session = this.sessionFactory.getCurrentSession(); 
		
		java.util.Date presentDate =new java.util.Date();
		Long dateinMS = presentDate.getTime();
		System.out.println("Transac ID is: "+userMoneyTransfer.getUserId().substring(0, 9)+ dateinMS.toString());
		
		userMoneyTransfer.setTransactionId(userMoneyTransfer.getUserId().substring(0, 9)+ dateinMS.toString());
		userMoneyTransfer.setDateCreated(new java.sql.Date(new java.util.Date().getTime()));
		
		UserAccountBalance accountBalance = svBankDao.getUserAccountBalance(userMoneyTransfer.getUserId());
		
		if(userMoneyTransfer.getFromAccount().substring(userMoneyTransfer.getFromAccount().length()-2).equals("01")){
			accountBalance.setChqAccBalance(""+(Integer.parseInt(accountBalance.getChqAccBalance())-Integer.parseInt(userMoneyTransfer.getAmount())));
		}else if(userMoneyTransfer.getFromAccount().substring(userMoneyTransfer.getFromAccount().length()-2).equals("02")){
			accountBalance.setSavAccBalance(""+(Integer.parseInt(accountBalance.getSavAccBalance())-Integer.parseInt(userMoneyTransfer.getAmount())));
		}else if(userMoneyTransfer.getFromAccount().substring(userMoneyTransfer.getFromAccount().length()-2).equals("03")){
			accountBalance.setCreditAccBalance(""+(Integer.parseInt(accountBalance.getCreditAccBalance())-Integer.parseInt(userMoneyTransfer.getAmount())));
		}
		
		if(transferType.equals("bAccounts")){
			
			userMoneyTransfer.setStatus("APPROVED");
			userMoneyTransfer.setDateApproved(new java.sql.Date(new java.util.Date().getTime()));
			
			if(userMoneyTransfer.getToAccount().substring(userMoneyTransfer.getToAccount().length()-2).equals("01")){
				accountBalance.setChqAccBalance(""+(Integer.parseInt(accountBalance.getChqAccBalance())+Integer.parseInt(userMoneyTransfer.getAmount())));
			}else if(userMoneyTransfer.getToAccount().substring(userMoneyTransfer.getToAccount().length()-2).equals("02")){
				accountBalance.setSavAccBalance(""+(Integer.parseInt(accountBalance.getSavAccBalance())+Integer.parseInt(userMoneyTransfer.getAmount())));
			}else if(userMoneyTransfer.getToAccount().substring(userMoneyTransfer.getToAccount().length()-2).equals("03")){
				accountBalance.setCreditAccBalance(""+(Integer.parseInt(accountBalance.getCreditAccBalance())+Integer.parseInt(userMoneyTransfer.getAmount())));
			}
			
		} else if(transferType.equals("oAccounts")){
			
			userMoneyTransfer.setStatus("PENDING");
		} 

		session.save(userMoneyTransfer);
		session.save(accountBalance);
		result = 1;

		return result;
	}

	public int addRecipient(UserMoneyRecipients userMoneyRecipients) {
		
		boolean isExisting = false;
		List<UserMoneyRecipients> existingRecipients = (List<UserMoneyRecipients>) this.sessionFactory.getCurrentSession().createQuery("from UserMoneyRecipients").list();
		
		for (UserMoneyRecipients recipient : existingRecipients) {
			if(recipient.getEmail().equalsIgnoreCase(userMoneyRecipients.getEmail())){
				isExisting = true;
			}
		}
		
		if(!isExisting){
		Session session = this.sessionFactory.getCurrentSession();
		session.save(userMoneyRecipients);
		return 1;
		}
		
		return 0;
	}
	
	public Map<String, String> getRecipientsAsMap(String userId) {
		
		
		List<UserMoneyRecipients> existingRecipients = (List<UserMoneyRecipients>) this.sessionFactory.getCurrentSession().createQuery("from UserMoneyRecipients").list();
		
		
		Map<String, String> existingRecipientsMap = new HashMap<String, String>();
		for (UserMoneyRecipients recipient : existingRecipients) {
			if(recipient.getUserId().equals(userId)){
				existingRecipientsMap.put(recipient.getEmail(), recipient.getNickname() +" - "+ recipient.getEmail());
			}
		}
		
		return existingRecipientsMap;
	}
	
	public List<UserMoneyTransfer> getRequests(String userId) {
		
		UserDetails userDetails = (UserDetails) this.sessionFactory.getCurrentSession().load(UserDetails.class, userId);
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("from UserMoneyTransfer where toAccount=:toAccount");
		query.setParameter("toAccount", userDetails.getEmail());
		
		List<UserMoneyTransfer> requests = (List<UserMoneyTransfer>) query.list();
		
		return requests;
	}
	
	public int updateTransferRequests(String transactionId, UserDetailsBean userdetails) {
		
		int result = 0;
		System.out.println("status in dao:"+userdetails.isStatus());
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("UPDATE UserMoneyTransfer SET status= :status where transactionId=:transactionId");
		if(userdetails.isStatus()){
			query.setParameter("status", "APPROVED");	
		} else {
			query.setParameter("status", "REJECTED");	
		}
		
		query.setParameter("transactionId", transactionId);
		result += query.executeUpdate();
		
		//if approved update receiver account balance
		if(userdetails.isStatus()) {
			
			UserAccountBalance accountBalance = svBankDao.getUserAccountBalance(userdetails.getUserId());
			System.out.println("in approved"+(Integer.parseInt(accountBalance.getChqAccBalance())+Integer.parseInt(userdetails.getAmount())));
			
			Query query1 = this.sessionFactory.getCurrentSession().createQuery("UPDATE UserAccountBalance SET chqAccBalance= :amount where userId=:userId");	
			query1.setParameter("amount", ""+(Integer.parseInt(accountBalance.getChqAccBalance())+Integer.parseInt(userdetails.getAmount())));
			query1.setParameter("userId", userdetails.getUserId());
			result += query1.executeUpdate();
			System.out.println("result is"+result);
			
		} else { //if rejected update sender account balance
			System.out.println("in rejected");
			
			Query query2 = this.sessionFactory.getCurrentSession().createQuery("from UserMoneyTransfer where transactionId= :transactionId");	
			query2.setParameter("transactionId", transactionId);
			
			List<UserMoneyTransfer> userMoneyTransfer = (List<UserMoneyTransfer>) query2.list();
			String senderAccNo = userMoneyTransfer.get(0).getFromAccount();
			
			String accountType = "";
			if(senderAccNo.substring(senderAccNo.length()-2).equals("01")){
				accountType = "chqAccBalance";
			}else if(senderAccNo.substring(senderAccNo.length()-2).equals("02")){
				accountType = "savAccBalance";
			}else if(senderAccNo.substring(senderAccNo.length()-2).equals("03")){
				accountType = "creditAccBalance";
			}
			
			
			UserAccountBalance accountBalance = svBankDao.getUserAccountBalance(senderAccNo.substring(0, senderAccNo.length()-2));
			
			Query query1 = this.sessionFactory.getCurrentSession().createQuery("UPDATE UserAccountBalance SET "+accountType+"= :accountType where userId=:userId");	
			if(accountType.equals("chqAccBalance")) {
				query1.setParameter("accountType", ""+(Integer.parseInt(accountBalance.getChqAccBalance())+Integer.parseInt(userdetails.getAmount())));
			} else if(accountType.equals("savAccBalance")) {
				query1.setParameter("accountType", ""+(Integer.parseInt(accountBalance.getSavAccBalance())+Integer.parseInt(userdetails.getAmount())));
			} else if(accountType.equals("creditAccBalance")) {
				query1.setParameter("accountType", ""+(Integer.parseInt(accountBalance.getCreditAccBalance())+Integer.parseInt(userdetails.getAmount())));
			}
			query1.setParameter("userId", senderAccNo.substring(0, senderAccNo.length()-2));
			result += query1.executeUpdate();
			
		}
		
		System.out.println("before return");
		return result;
	}

	public String withdrawMoney(UserMoneyWithdraw userMoneyWithdraw) {
		  
		  int result=0;
		  Session session = this.sessionFactory.getCurrentSession(); 
		  
		  java.util.Date presentDate =new java.util.Date();
		  Long dateinMS = presentDate.getTime();
		  System.out.println("Transac ID for withdraw is: "+userMoneyWithdraw.getUserId().substring(0, 9)+ dateinMS.toString());
		  
		  userMoneyWithdraw.setTransactionId(userMoneyWithdraw.getUserId().substring(0, 9)+ dateinMS.toString());
		  userMoneyWithdraw.setDateTransferred(new java.sql.Date(new java.util.Date().getTime()));
		  String moneyCardNo = getMoneyCardNo(userMoneyWithdraw.getUserId().substring(8, 12));
		  userMoneyWithdraw.setMoneyCardNo(moneyCardNo);
		  
		  UserAccountBalance accountBalance = svBankDao.getUserAccountBalance(userMoneyWithdraw.getUserId());
		  
		  if(userMoneyWithdraw.getFromAccount().substring(userMoneyWithdraw.getFromAccount().length()-2).equals("01")){
		   System.out.println("cheq");
		   accountBalance.setChqAccBalance(""+(Integer.parseInt(accountBalance.getChqAccBalance())-Integer.parseInt(userMoneyWithdraw.getAmount())));
		  }else if(userMoneyWithdraw.getFromAccount().substring(userMoneyWithdraw.getFromAccount().length()-2).equals("02")){
		   System.out.println("cheq");
		   accountBalance.setSavAccBalance(""+(Integer.parseInt(accountBalance.getSavAccBalance())-Integer.parseInt(userMoneyWithdraw.getAmount())));
		  }else if(userMoneyWithdraw.getFromAccount().substring(userMoneyWithdraw.getFromAccount().length()-2).equals("03")){
		   System.out.println("cheq");
		   accountBalance.setCreditAccBalance(""+(Integer.parseInt(accountBalance.getCreditAccBalance())-Integer.parseInt(userMoneyWithdraw.getAmount())));
		  }
		  
		  session.save(userMoneyWithdraw);
		  session.save(accountBalance);
		  result = 1;

		  return moneyCardNo;
		 }
		 
		 public List<UserMoneyDeposit> getDepositHistoryForUser(String userId){
		  System.out.println("user id in get admin hisotry method is : "+userId);
		  Session session = this.sessionFactory.getCurrentSession(); 
		  String hql = "FROM UserMoneyDeposit WHERE userId = :userId";
		  Query query = session.createQuery(hql);
		  query.setParameter("userId",userId);
		  List<UserMoneyDeposit> results = query.list();
		  System.out.println("list size from data base is : "+results.size());
		  return results;
		 }
		 
		 public int depositMoney(UserMoneyDeposit userMoneyDeposit) {
		  int result=0;
		  Session session = this.sessionFactory.getCurrentSession(); 
		  java.util.Date presentDate =new java.util.Date();
		  Long dateinMS = presentDate.getTime();
		  System.out.println("Transac ID for deposit is: "+userMoneyDeposit.getUserId().substring(0, 9)+ dateinMS.toString());
		  userMoneyDeposit.setTransactionId(userMoneyDeposit.getUserId().substring(0, 9)+ dateinMS.toString());
		  userMoneyDeposit.setDateTransferred(new java.sql.Date(new java.util.Date().getTime()));
		  userMoneyDeposit.setStatus("Pending");
		  
		  session.save(userMoneyDeposit);
		  result = 1;

		  return result;
		 }
		 
		 public String getMoneyCardNo(String prefix) {
		  int length = 4;
		  char[] characterSet = "0123456789".toCharArray();
		     Random random = new SecureRandom();
		     char[] result = new char[length];
		     for (int i = 0; i < result.length; i++) {
		         int randomCharIndex = random.nextInt(characterSet.length);
		         result[i] = characterSet[randomCharIndex];
		     }
		     String cardNo = prefix+new String(result);
		     System.out.println("money card no is : "+cardNo);
		     return cardNo;
		 }
	
}
