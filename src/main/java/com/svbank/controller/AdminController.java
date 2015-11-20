package com.svbank.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svbank.service.AdminService;
import com.svbank.service.SVBankService;
import com.svbank.entity.AccountRequests;
import com.svbank.entity.UserMoneyDeposit;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SVBankService svbankService;
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "/accountrequests", method = RequestMethod.GET)
	public String accountRequests(ModelMap map) {
		System.out.println("into account requests");
		
		List<AccountRequests> accountRequests = adminService.getAccountRequests();
		System.out.println("total no of requests:"+accountRequests);
		map.addAttribute("accountRequests",accountRequests);
		return "admin/accountRequests";
	}
	
	@RequestMapping(value = "/accReqDet/{reqId}", method = RequestMethod.GET)
	public String accReqDetails(@PathVariable String reqId, ModelMap map) {
		System.out.println("into account request details");
		
		List<AccountRequests> accountRequests = adminService.getAccountRequests();
		for(AccountRequests request : accountRequests){
			if(request.getReqId().equals(reqId)){
				map.addAttribute("accountRequestDetails",request);
				map.addAttribute("status",request);
			}
		}
		return "admin/accountRequestDetails";
	}
	
	@RequestMapping(value = "/accReqDet/changeRequestStatus", method = RequestMethod.POST)
	 public String changeRequestStatus(@ModelAttribute("status") AccountRequests statusChgRequest, ModelMap map) {
	  System.out.println("into account request status change with new status:"+statusChgRequest.getStatus()+", for "+statusChgRequest.getReqId());
	  System.out.println(statusChgRequest.getFirstName());
	  System.out.println(statusChgRequest.getEmail());
	  System.out.println(statusChgRequest.getGender());
	  int approvalStatus = adminService.approveRejectRequests(statusChgRequest);
	  System.out.println("approvalStatus is : "+approvalStatus);
	  return "redirect:/admin/accountRequests";
	 }

	@RequestMapping(value = "/depositrequests", method = RequestMethod.GET)
	 public String depositRequests(ModelMap map) {
	  System.out.println("into admin deposit requests");
	  List<UserMoneyDeposit> depositRequests = adminService.getDepositRequests();
	  System.out.println("total no of requests:"+depositRequests.size());
	  map.addAttribute("depositRequests",depositRequests);
	  return "admin/depositRequests";
	 }

	 @RequestMapping(value = {"/download/{transactionId}"}, method = RequestMethod.GET)
	    public String download(@PathVariable("transactionId")
	            String transactionId, HttpServletResponse response) {
	  System.out.println("download successfull");
	  UserMoneyDeposit cheque = adminService.getChequeForTID(transactionId);
	        try {
	          response.setHeader("Content-Disposition", "inline;filename=\"" +cheque.getTransactionId()+ "\"");
	             OutputStream out = response.getOutputStream();
	             response.setContentType("image/gif");
	             IOUtils.copy(cheque.getChequeImg().getBinaryStream(), out);
	             out.flush();
	             out.close();
	         
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return "redirect:admin/depositRequests";
	    }
}
