package com.svbank.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.svbank.bean.UserDetailsBean;
import com.svbank.entity.AccountRequests;
import com.svbank.entity.UserAccountBalance;
import com.svbank.entity.UserAccounts;
import com.svbank.entity.UserDetails;
import com.svbank.entity.UserLogin;
import com.svbank.entity.UserMoneyDeposit;
import com.svbank.entity.UserRole;

public class AdminDaoImpl implements AdminDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@SuppressWarnings("unchecked")
	public List<AccountRequests> getAccountRequests() {
		return (List<AccountRequests>) this.sessionFactory.getCurrentSession()
				.createQuery("from AccountRequests where status = 1").list();
	}

	public int approveRejectRequests(AccountRequests statusChgRequest) {

		System.out.println("in approval impl" + statusChgRequest.getStatus()+"stat:"+statusChgRequest.getReqId());
		Session session = this.sessionFactory.getCurrentSession();

		SVBankDaoImpl svBankDaoImpl = new SVBankDaoImpl();
		UserDetailsBean userDetailsBean = new UserDetailsBean();
		userDetailsBean.setFirstName(statusChgRequest.getFirstName());

		session.beginTransaction();
		String defaultPwd = getDefaultPwd();
		String encodedPassword = passwordEncoder.encodePassword(defaultPwd,
				null);
		System.out.println("encodeed pass is : " + encodedPassword);
		if (statusChgRequest.getStatus() == 2) {
			System.out.println("in approved");
			UserLogin userLogin = new UserLogin();
			userLogin.setDefaultPwd(true);
			userLogin.setPassword(defaultPwd);
			userLogin.setEnabled(true);
			session.save(userLogin);

			System.out.println("in mail approved");
			List<BigInteger> maxUID = this.sessionFactory.getCurrentSession()
					.createQuery("select max(username) from UserLogin").list();
			System.out.println("user id from data base is : "
					+ maxUID.get(0).toString());

			UserRole userRole = new UserRole();
			UserDetails userDetails = new UserDetails();
			UserAccounts userAccounts = new UserAccounts();
			UserAccountBalance userAccountBalance = new UserAccountBalance();

			userRole.setUserId(maxUID.get(0).toString());
			userRole.setUserRole("ROLE_USER");

			userDetails.setUserId(maxUID.get(0).toString());
			userDetails.setFirstName(statusChgRequest.getFirstName());
			userDetails.setLastName(statusChgRequest.getLastName());
			userDetails.setGender(statusChgRequest.getGender());
			userDetails.setDob(statusChgRequest.getDob());
			userDetails.setAddress(statusChgRequest.getAddress());
			userDetails.setPhoneNumber(statusChgRequest.getPhoneNumber());
			userDetails.setEmail(statusChgRequest.getEmail());

			System.out.println("user id to set is:" + userDetails.getUserId());
			userAccounts.setUserId(maxUID.get(0).toString());
			userAccounts.setChqAcc(userDetails.getUserId() + "01");
			userAccounts.setSavAcc(userDetails.getUserId() + "02");
			userAccounts.setCreditAcc(userDetails.getUserId() + "03");

			userAccountBalance.setUserId(maxUID.get(0).toString());
			userAccountBalance.setChqAccBalance("200");
			userAccountBalance.setSavAccBalance("0");
			userAccountBalance.setCreditAccBalance("1000");

			session.save(userRole);
			session.save(userDetails);
			session.save(userAccounts);
			session.save(userAccountBalance);

			userDetailsBean.setUserId(maxUID.get(0).toString());
			userDetailsBean.setPassword(defaultPwd);
			svBankDaoImpl.mailingService("accountRequestApproved",
					statusChgRequest.getEmail(), userDetailsBean);

		} else if (statusChgRequest.getStatus() == 3) {
			System.out.println("in rejected");
			svBankDaoImpl.mailingService("accountRequestDenied",
					statusChgRequest.getEmail(), userDetailsBean);
		}
		
		Query query = this.sessionFactory.getCurrentSession().createQuery("update AccountRequests set status= :status where reqId= :reqId");
		query.setParameter("status", statusChgRequest.getStatus());
		query.setParameter("reqId", statusChgRequest.getReqId());
		query.executeUpdate();
		
		return 0;
	}

	public List<UserMoneyDeposit> getDepositRequests(){
		  Session session = this.sessionFactory.getCurrentSession(); 
		  String hql = "FROM UserMoneyDeposit WHERE status = :status";
		  Query query = session.createQuery(hql);
		  query.setParameter("status","Pending");
		  List<UserMoneyDeposit> results = query.list();
		  System.out.println("list size from data base is : "+results.size());
		  return results;
		 }
		 
	public UserMoneyDeposit getChequeForTID(String transactionId) {
		  Session session = sessionFactory.getCurrentSession();
		        return (UserMoneyDeposit)session.get(UserMoneyDeposit.class, transactionId);
		 }
		 
	public String getDefaultPwd() {
		int length = 6;
		char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		Random random = new SecureRandom();
		char[] result = new char[length];
		for (int i = 0; i < result.length; i++) {
			int randomCharIndex = random.nextInt(characterSet.length);
			result[i] = characterSet[randomCharIndex];
		}
		return new String(result);
	}

}
