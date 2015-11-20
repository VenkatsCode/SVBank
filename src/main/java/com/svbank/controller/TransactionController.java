package com.svbank.controller;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.svbank.bean.UserDetailsBean;
import com.svbank.dao.SVBankDaoImpl;
import com.svbank.entity.UserMoneyDeposit;
import com.svbank.entity.UserMoneyRecipients;
import com.svbank.entity.UserMoneyTransfer;
import com.svbank.entity.UserMoneyWithdraw;
import com.svbank.entity.UserSecurity;
import com.svbank.service.SVBankService;
import com.svbank.service.TransactionService;

@Controller
public class TransactionController {

	@Autowired
	SVBankService svbankService;
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping("/transac/btransferHome")
	public String transferBetweenAccounts(ModelMap model, HttpServletRequest request) {
		
		String userId = request.getUserPrincipal().getName();
		model.addAttribute("userAccountsMap", svbankService.getUserAccountsAsMap(userId));
		model.addAttribute("userAccountBalance", svbankService.getUserAccountBalance(userId));
		model.addAttribute("bAccounts", new UserMoneyTransfer());
		return "transfers/betweenAccounts";
	}
	
	@RequestMapping("/transac/otransferHome")
	public String transferToOtherAccounts(ModelMap model, HttpServletRequest request) {
		System.out.println("other accounts");
		String userId = request.getUserPrincipal().getName();
		model.addAttribute("userAccountsMap", svbankService.getUserAccountsAsMap(userId));
		model.addAttribute("recipientsMap", transactionService.getRecipientsAsMap(userId));
		model.addAttribute("userAccountBalance", svbankService.getUserAccountBalance(userId));
		model.addAttribute("oAccounts", new UserMoneyTransfer());
		model.addAttribute("receps", new UserMoneyRecipients());
		return "transfers/otherAccounts";
	}
	
	@RequestMapping("/transac/transferRequestsHome")
	public String transferRequestsHome(ModelMap model, HttpServletRequest request) {
		System.out.println("transferRequestsHome");
		String userId = request.getUserPrincipal().getName();
		System.out.println("requestList size:"+ transactionService.getRequests(userId).size());
		model.addAttribute("requestList", transactionService.getRequests(userId));
		
		return "transfers/transferRequests";
	}
	
	@RequestMapping(value = "/transac/transferAction", method = RequestMethod.GET)
	public String transferAction(@RequestParam(value = "flag", required = true) boolean status,
			@RequestParam(value = "id", required = true) String transactionId,
			@RequestParam(value = "amt", required = true) String amount,ModelMap model, HttpServletRequest request) {
		System.out.println("transferAction in controller:"+status);
		System.out.println("transferAction in controller:"+transactionId);
		System.out.println("transferAction in controller:"+amount);
		
		String userId = request.getUserPrincipal().getName();
		
		UserDetailsBean transacDetails = new UserDetailsBean();
		transacDetails.setUserId(userId);
		transacDetails.setStatus(status);
		transacDetails.setAmount(amount);
		
		int result = transactionService.updateTransferRequests(transactionId, transacDetails);
		System.out.println("result is"+result);
		
		if(result == 0){
			
		model.addAttribute("accRegMessage","Transaction Successful");
		}else {
			
		System.out.println("in else: transfer successful");
		
		svbankService.mailingService("transferMoneyApproval", svbankService.getUserDetails(userId).getEmail(), transacDetails);
		model.addAttribute("requestList", transactionService.getRequests(userId));
		model.addAttribute("accRegMessage","Transaction successful");
		}
		
		return "transfers/transferRequests";
	}
	
	@RequestMapping(value = "/transac/addRecep", method = RequestMethod.POST)
	public String addRecepients(@ModelAttribute("receps") UserMoneyRecipients UserMoneyRecipients, ModelMap model, HttpServletRequest request) {
		
		System.out.println("add recipients for:"+request.getUserPrincipal().getName());
		UserMoneyRecipients.setUserId(request.getUserPrincipal().getName());
		int result = transactionService.addRecipient(UserMoneyRecipients);
		
		if(result == 0){
			model.addAttribute("accRegMessage","Recipient already exists");
		}else {
			
			model.addAttribute("accRegMessage","Recipient added");
		}

		return "redirect:/transac/otransferHome";
	}
	
	@RequestMapping(value = "/transac/btransfer", method = RequestMethod.POST)
	public String transferB(@ModelAttribute("bAccounts") UserMoneyTransfer userMoneyTransfer, ModelMap model, HttpServletRequest request) {
		
		userMoneyTransfer.setUserId(request.getUserPrincipal().getName());
		int result = transactionService.transferMoney(userMoneyTransfer, "bAccounts");
		
		if(result == 0){
			model.addAttribute("accRegMessage","Transfer Failure");
		}else {
			
			model.addAttribute("accRegMessage","Transfer Successful");
		}

		return "redirect:/accounts";
	}
	
	@RequestMapping(value = "/transac/otransfer", method = RequestMethod.POST)
	public String transferO(@ModelAttribute("oAccounts") UserMoneyTransfer userMoneyTransfer, ModelMap model, HttpServletRequest request) {
		
		userMoneyTransfer.setUserId(request.getUserPrincipal().getName());
		
		UserSecurity secDetails = svbankService.getUseSecurity(request.getUserPrincipal().getName());
		int result;
		
		if(request.getParameter("securityPin").equals(secDetails.getSecurityCode()))
		{
		result = transactionService.transferMoney(userMoneyTransfer, "oAccounts");
		}else {
		result = 0;
		}
		
		if(result == 0){
			
			System.out.println("in if: transfer failure");
			model.addAttribute("accRegMessage","Transfer Failed !!! Please try again with correct details !!!");
		}else {
			
			//TODO - send mail to money sender (first get mail of sender from transaction ID)
			/*System.out.println("in else: transfer successful");
			UserDetailsBean userDetails = new UserDetailsBean();
			userDetails.setAmount(userMoneyTransfer.getAmount());
			svbankService.mailingService("transferMoney", userMoneyTransfer.getToAccount(), userDetails);*/
			
			model.addAttribute("accRegMessage","Transfer Successful");
		}

		return "redirect:/accounts";
	}
	
	@RequestMapping("/transac/withdraw")
	 public String withdraw(ModelMap model, HttpServletRequest request) {
	  String userId = request.getUserPrincipal().getName();
	  System.out.println("user id in withdraw method is : "+userId);
	  model.addAttribute("userAccountsMap", svbankService.getUserAccountsAsMap(userId));
	  model.addAttribute("userAccountBalance", svbankService.getUserAccountBalance(userId));
	  model.addAttribute("wAmount", new UserMoneyWithdraw());
	  return "transfers/withdrawMoneycard";
	 }
	 
	 @RequestMapping(value = "/transac/wAmount", method = RequestMethod.POST)
	 public String wAmount(@ModelAttribute("wAmount") UserMoneyWithdraw userMoneyWithdraw, ModelMap model, HttpServletRequest request) {
	  System.out.println("security key is : "+request.getParameter("securityPin"));
	  userMoneyWithdraw.setUserId(request.getUserPrincipal().getName());
	  String moneyCardNo = transactionService.withdrawMoney(userMoneyWithdraw);
	  System.out.println("moneyCardNo is : "+moneyCardNo);
	  return "redirect:/accounts";
	 }
	 
	 @RequestMapping("/transac/dHistory")
	 public String depositHistory(ModelMap model, HttpServletRequest request) {
	  String userId = request.getUserPrincipal().getName();
	  System.out.println("user id in deposit history method is : "+userId);
	  model.addAttribute("depositHistory", transactionService.getDepositHistoryForUser(userId));
	  return "transfers/depositHistory";
	 }
	 
	 @RequestMapping("/transac/deposit")
	 public String deposit(ModelMap model, HttpServletRequest request) {
	  String userId = request.getUserPrincipal().getName();
	  System.out.println("user id in deposit method is : "+userId);
	  model.addAttribute("userAccountsMap", svbankService.getUserAccountsAsMap(userId));
	  model.addAttribute("userAccountBalance", svbankService.getUserAccountBalance(userId));
	  model.addAttribute("depositAmount", new UserMoneyDeposit());
	  return "transfers/depositMoney";
	 }
	 
	 @RequestMapping(value = "/transac/depositAmount", method = RequestMethod.POST)
	 public String dAmount(@RequestParam(value ="file", required = false) MultipartFile chequeImg,@ModelAttribute("depositAmount") UserMoneyDeposit userMoneyDeposit,
	   ModelMap model, HttpServletRequest request) {
	  System.out.println("in deposit amount method");
	  System.out.println("File:" + chequeImg.getName());
	  userMoneyDeposit.setUserId(request.getUserPrincipal().getName());
	  try {
	            Blob blob = Hibernate.createBlob(chequeImg.getInputStream());
	            userMoneyDeposit.setChequeImg(blob);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	  int result = transactionService.depositMoney(userMoneyDeposit);
	  System.out.println("deposit result is : "+result);
	  return "redirect:/transac/dHistory";
	 }
	
}
