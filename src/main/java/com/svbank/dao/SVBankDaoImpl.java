package com.svbank.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.svbank.bean.UserDetailsBean;
import com.svbank.entity.AccountRequests;
import com.svbank.entity.SecurityQuestions;
import com.svbank.entity.UserAccountBalance;
import com.svbank.entity.UserAccounts;
import com.svbank.entity.UserDetails;
import com.svbank.entity.UserLogin;
import com.svbank.entity.UserSecurity;

@Repository
public class SVBankDaoImpl implements SVBankDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public int updatePassword(UserDetailsBean user, boolean setSecurity) {
		
		int result=0;
		
		if(setSecurity){
			
			System.out.println("user is:"+user.getUserId());
			String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), null);
			Query query = this.sessionFactory.getCurrentSession().createQuery("update UserLogin set password= :password, defaultPwd= :defaultPwd where username= :username");
			query.setParameter("password", user.getPassword());
			query.setParameter("defaultPwd", false);
			query.setParameter("username", new BigInteger(user.getUserId()));
			result = query.executeUpdate();
			System.out.println("pwd set ?"+result);
			if(result == 0){
				return 0;
			}
			
			
			this.sessionFactory.getCurrentSession().beginTransaction();
			UserSecurity userSecurity = new UserSecurity();
			userSecurity.setUserId(user.getUserId());
			userSecurity.setSecurityQuestion1(user.getSecurityQuestion1());
			userSecurity.setSecurityAnswer1(user.getSecurityAnswer1());
			userSecurity.setSecurityQuestion2(user.getSecurityQuestion2());
			userSecurity.setSecurityAnswer2(user.getSecurityAnswer2());
			this.sessionFactory.getCurrentSession().save(userSecurity);
			
			return 1;
			
		}else {
			
			Query query = this.sessionFactory.getCurrentSession().createQuery("update UserLogin set password= :password where username= :username");
			query.setParameter("password", user.getPassword());
			query.setParameter("username", new BigInteger(user.getUserId()));
			result = query.executeUpdate();
			return result;
		}
		
	}
	
	public UserAccounts getUserAccounts(String userId) {
		
		UserAccounts accounts = (UserAccounts) this.sessionFactory.getCurrentSession().load(UserAccounts.class, userId);
		Hibernate.initialize(accounts);
		return accounts;
	}
	
	public Map<String, String> getUserAccountsAsMap(String userId) {
		
		UserAccounts accounts = (UserAccounts) this.sessionFactory.getCurrentSession().load(UserAccounts.class, userId);
		Hibernate.initialize(accounts);
		
		Map<String, String> accountsMap = new HashMap<String, String>();
		accountsMap.put(accounts.getChqAcc(), "Chequing - "+accounts.getChqAcc());
		accountsMap.put(accounts.getSavAcc(), "Saving - "+accounts.getSavAcc());
		accountsMap.put(accounts.getCreditAcc(), "Credit - "+accounts.getCreditAcc());
		
		return accountsMap;
	}
	
	public UserAccountBalance getUserAccountBalance(String userId) {
		
		UserAccountBalance accountBalance = (UserAccountBalance) this.sessionFactory.getCurrentSession().load(UserAccountBalance.class, userId);
		Hibernate.initialize(accountBalance);
		return accountBalance;
	}
	
	@SuppressWarnings("unchecked")
	public int registerUser(UserDetailsBean userDetails){ 
	  System.out.println("in register user dao implwww");
	  System.out.println("user first name is : "+userDetails.getFirstName());
	  System.out.println("user first name is : "+userDetails.getEmail());

	  Session session = this.sessionFactory.getCurrentSession(); 
	  
	  int retVal = 0;
	  
	  String hql = "FROM AccountRequests WHERE email = :email";
	  Query query = session.createQuery(hql);
	  query.setParameter("email",userDetails.getEmail());
	  List<UserLogin> results = query.list();
	  System.out.println("list.size is : "+results.size());
	
	  session.beginTransaction();
	  AccountRequests accountRequest = new AccountRequests();
	  accountRequest.setFirstName(userDetails.getFirstName());
	  accountRequest.setLastName(userDetails.getLastName());
	  accountRequest.setGender(userDetails.getGender());
	  accountRequest.setDob(userDetails.getDob());
	  accountRequest.setAddress(userDetails.getAddress());
	  accountRequest.setPhoneNumber(userDetails.getPhoneNumber());
	  accountRequest.setEmail(userDetails.getEmail());
	  accountRequest.setStatus(1);

	  //Save the user in database
	  session.save(accountRequest);
	  
	  //mail user
	  System.out.println("in else: transfer successful");
		UserDetailsBean userDetailsBean = new UserDetailsBean();
		userDetailsBean.setFirstName(userDetails.getFirstName());
		
		retVal = mailingService("registerUser", userDetails.getEmail(), userDetailsBean);
	  
	  System.out.println("return value is : "+retVal);
	  return retVal;
	 }
	
	public UserDetails getUserDetails(String userId) {
		UserDetails accountBalance = (UserDetails) this.sessionFactory.getCurrentSession().load(UserDetails.class, userId);
		Hibernate.initialize(accountBalance);
		return accountBalance;
	}
	
	public UserSecurity getUseSecurity(String userId) {
		UserSecurity securityDetails = (UserSecurity) this.sessionFactory.getCurrentSession().load(UserSecurity.class, userId);
		Hibernate.initialize(securityDetails);
		return securityDetails;
	}
	
	public Map<Integer,String> getSecurityQuestions() {
		List<SecurityQuestions> list = (List<SecurityQuestions>) this.sessionFactory.getCurrentSession().createQuery("from SecurityQuestions").list();
		Map<Integer,String> questionsMap= new HashMap<Integer,String>();
		for(int i=0;i<list.size();i++){
			questionsMap.put(list.get(i).getQuestionId(), list.get(i).getQuestion());
	    }
		return questionsMap;
	}
	
	public String getSecurityQuestionById(int id) {
		
		Query q = this.sessionFactory.getCurrentSession().createQuery("from SecurityQuestions where questionId=:questionId");
		q.setParameter("questionId",id);
		
		List<SecurityQuestions> secQuestion = (List<SecurityQuestions>) q.list();
		
		return secQuestion.get(0).getQuestion();
	}
	
	public int updateUserDetails(UserDetails userDetails) {
		   System.out.println("in update user dao impl : "+userDetails.getUserId());
		   System.out.println("user first name is : "+userDetails.getFirstName());
		   System.out.println("user first name is : "+userDetails.getEmail());
		   Session session = this.sessionFactory.getCurrentSession(); 
		   int retVal = 0;
		   String hql = "FROM UserDetails WHERE email = :email";
		   Query query = session.createQuery(hql);
		   query.setParameter("email",userDetails.getEmail());
		   List<UserLogin> results = query.list();
		   System.out.println("list.size is : "+results.size());
		   if(results.size()>1)
		   {
		   retVal = 2;
		   }
		   else
		   {
		   session.beginTransaction();
		   session.merge(userDetails);
		   retVal = 1;
		   }
		   System.out.println("return value is : "+retVal);
		   return retVal;
		 
		 }
	
	public boolean isDefaultPwd(String userId) {
		Query q = this.sessionFactory.getCurrentSession().createQuery("from UserLogin where username=:username");
		q.setParameter("username",new BigInteger(userId));
		
		List<UserLogin> userLogin = (List<UserLogin>) q.list();
		if(userLogin.get(0).isDefaultPwd()){
			return true;
		}
		
		return false;
	}
	
	public int mailingService(String event, String receiverEmail, UserDetailsBean userdetails) {
    	
		  System.out.println("into mailing service"+receiverEmail);
		  
	  	  String to = receiverEmail;
	  	  String from = "svbank6260@gmail.com";
	  	     
	  	     String  d_host = "smtp.gmail.com",
	  	             d_port  = "465";
	  	     Properties props = new Properties();
	  	     props.put("mail.smtp.user", from);
	  	     props.put("mail.smtp.host", d_host);
	  	     props.put("mail.smtp.port", d_port);
	  	     props.put("mail.smtp.starttls.enable","true");
	  	     props.put("mail.smtp.debug", "true");
	  	     props.put("mail.smtp.auth", "true");
	  	     props.put("mail.smtp.socketFactory.port", d_port);
	  	     props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  	     props.put("mail.smtp.socketFactory.fallback", "false");

	  	     // Setup mail server
	  	    // javax.mail.Session mailsession = javax.mail.Session.getDefaultInstance(properties);
	  	     javax.mail.Session mailsession = javax.mail.Session.getInstance(props,new GMailAuthenticator("svbank6260@gmail.com", "inse6260"));
	  	     try{
	  	      System.out.println("in try mail");
	  	         // Create a default MimeMessage object.
	  	         MimeMessage message = new MimeMessage(mailsession);

	  	         // Set From: header field of the header.
	  	         message.setFrom(new InternetAddress(from));

	  	         // Set To: header field of the header.
	  	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	  	         // Set Subject: header field
	  	         message.setSubject("DONOT REPLY!! SV-Bank Services");

	  	         // Now set the actual message
	  	         if(event.equals("registerUser")){
	  	        	 
	  	        	message.setText("Hi "+userdetails.getFirstName()+
	  	   	             " Thanks for registering with our bank, you will hear very soon about the request status. Have a great day :).");
	  	        	
	  	         } else if(event.equals("transferMoney")){
	  	        	
	  	        	System.out.println("in transferMoney event");
	  	        	message.setContent("Hello <b>XYZ</b> <br/> "
	  	        			+ "<b>ABC</b> sent you amount <br/><hr/><br/>"
	  	        			+ "Deposit Money <a href='http://localhost:8080/SVBank/transac/transferRequestsHome'>SV Bank</a>", "text/html; charset=utf-8");
	  	         } else if(event.equals("transferMoneyApproval")){
		  	        	
	  	        	 String status;
	  	        	 if(userdetails.isStatus()){
	  	        		 status = "APPROVED";
	  	        	 } else {
	  	        		 status = "REJECTED";
	  	        	 }
	  	        	 
		  	        	System.out.println("in transferMoneyapproval event");
		  	        	message.setContent("Hello <b>XYZ</b> <br/> "
		  	        			+ "<b>ABC</b> has"
		  	        			+ status
		  	        			+" your money !!!", "text/html; charset=utf-8");
		  	     } else if(event.equals("accountRequestApproved")){
		  	    	message.setText("Hi "+userdetails.getFirstName()+
				             "Your userID is : "+userdetails.getUserId()+
				             "and pass word is : "+userdetails.getPassword());
		  	     } else if(event.equals("accountRequestDenied")){
		  	    	 message.setText("Hi "+userdetails.getFirstName()+
				             "Your Request has been rejected. Sorry for the inconvinence please contact bank for furthur details.");
		  	     }
	  	         
	  	         // Send message
	  	         Transport.send(message);
	  	         System.out.println("Mail sent successfully....");
	  	      }catch (MessagingException mex) {
	  	         mex.printStackTrace();
	  	      }
	  	     
	    	return 0;
	    }
	
	public int setPin(UserDetailsBean pinDetails) {
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("update UserSecurity set securityCode= :pin where userId= :userId");
		query.setParameter("pin", pinDetails.getPassword());
		query.setParameter("userId", pinDetails.getUserId());
		
		return query.executeUpdate();
	}
	
	
}
