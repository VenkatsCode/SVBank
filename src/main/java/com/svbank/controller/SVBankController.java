package com.svbank.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svbank.bean.UserDetailsBean;
import com.svbank.entity.UserDetails;
import com.svbank.entity.UserSecurity;
import com.svbank.service.SVBankService;

@Controller
class SVBankController {

	String userCardNo;
	
	@Autowired
	private SVBankService svbankService;
	
	public void setSvbankService(SVBankService svbankService){
		this.svbankService = svbankService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(ModelMap map) {
		return "redirect:/home";
	}
	
	@RequestMapping("/forgotpassword")
	public String forgotPassword(ModelMap model) {
		model.addAttribute("enterLoginId", true);
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String resetPassword(ModelMap model, HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		System.out.println("userId in resetpassword is : "+userId);
		UserDetailsBean userDetails = new UserDetailsBean();
		userDetails.setUserId(userId);
		
		UserSecurity secDetails = svbankService.getUseSecurity(userId);
		model.addAttribute("securityQuestion1",svbankService.getSecurityQuestionById(secDetails.getSecurityQuestion1()));
		model.addAttribute("securityQuestion2",svbankService.getSecurityQuestionById(secDetails.getSecurityQuestion2()));
		model.addAttribute("enterLoginId", false);
		model.addAttribute("forgotPwd", userDetails);
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/setnewpassword", method = RequestMethod.POST)
	public String setNewPassword(@ModelAttribute("forgotPwd") UserDetailsBean userDetails,
	ModelMap model, HttpServletRequest request) {
		
		String userId = userDetails.getUserId();
		System.out.println("userId in resetpassword is : "+userId);
		
		UserSecurity secDetails = svbankService.getUseSecurity(userId);
		if(userDetails.getSecurityAnswer1().equals(secDetails.getSecurityAnswer1()) && userDetails.getSecurityAnswer2().equals(secDetails.getSecurityAnswer2()))
		{
			int result = svbankService.updatePassword(userDetails, false);
			if(result==1){
			model.addAttribute("message","Password Successfully Changed");
			}
			else{
				model.addAttribute("message","Password Change Failed !!! Try again !!!");
			}
			
		} else {
		
			model.addAttribute("message","Password Change Failed !!! Try again with correct details !!!");
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("userDetails", new UserDetailsBean());
		return "registerUser";
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	 public String registerUser(@ModelAttribute("userDetails") UserDetailsBean userDetails,
	   BindingResult result,Model model) {
	  System.out.println("test");
	  int redirect = svbankService.registerUser(userDetails);
	  if(redirect == 2)
	  {
	   model.addAttribute("registerMessage","User is already registered");
	  }
	  else if(redirect == 1)
	  {
	   model.addAttribute("registerMessage","User registered successfully");
	  }
	  else if(redirect == 0)
	  {
	   model.addAttribute("registerMessage","Unable to register please try again later");
	  }
	  return "login";
	 }
	
	@RequestMapping("/accounts")
	public String accounts(ModelMap model, HttpServletRequest request) {
		
		String userId = request.getUserPrincipal().getName();
		System.out.println("userId"+userId);
		model.addAttribute("userAccounts", svbankService.getUserAccounts(userId));
		model.addAttribute("userAccountBalance", svbankService.getUserAccountBalance(userId));
		model.addAttribute("totalAccountBalance", 
				Integer.parseInt(svbankService.getUserAccountBalance(userId).getChqAccBalance())+Integer.parseInt(svbankService.getUserAccountBalance(userId).getSavAccBalance()));
		return "accountSummary";
	}
	
	@RequestMapping(value = "/security", method = RequestMethod.GET)
	public String security(ModelMap model, HttpServletRequest request) {
		
		Map<Integer,String> secQuestions = svbankService.getSecurityQuestions();
		System.out.println("SQs"+secQuestions.size());
		
		UserSecurity secDetails = svbankService.getUseSecurity(request.getUserPrincipal().getName());
		model.addAttribute("securityQuestion1",svbankService.getSecurityQuestionById(secDetails.getSecurityQuestion1()));
		model.addAttribute("securityQuestion2",svbankService.getSecurityQuestionById(secDetails.getSecurityQuestion2()));
		
		if(svbankService.getUseSecurity(request.getUserPrincipal().getName()).getSecurityCode()==null){
			model.addAttribute("securityPinMessage","Security Pin not yet set.");
			model.addAttribute("securityPinStatus",false);
		} else{
			model.addAttribute("securityPinMessage","Security Pin already set.");
			model.addAttribute("securityPinStatus",true);
			model.addAttribute("securityPin",svbankService.getUseSecurity(request.getUserPrincipal().getName()).getSecurityCode());
		}
		model.addAttribute("securityHomePage", true);
		model.addAttribute("setPin", new UserDetailsBean());
		return "securityPin";
	}
	
	@RequestMapping(value = "/setnewpin", method = RequestMethod.POST)
	  public String setnewpin(@ModelAttribute("setPin") UserDetailsBean userDetails,
			  HttpServletRequest request, ModelMap model) {
		
		userDetails.setUserId(request.getUserPrincipal().getName());
		UserSecurity secDetails = svbankService.getUseSecurity(request.getUserPrincipal().getName());
		if(userDetails.getSecurityAnswer1().equals(secDetails.getSecurityAnswer1()) && userDetails.getSecurityAnswer2().equals(secDetails.getSecurityAnswer2()))
		{
			int result = svbankService.setPin(userDetails);
			if(result==1){
			model.addAttribute("message","Pin Set Successfully");
			System.out.println("Pin Set Successfully");
			}
			else{
				model.addAttribute("message","Pin Set failed !!! Try again !!!");
			}
			
		} else {
		
			model.addAttribute("message","Pin Set failed !!! Try again with correct details !!!");
		}
		return "redirect:/security";
	  }
	
	@RequestMapping(value = "/viewSetSecurity", method = RequestMethod.GET)
	public String viewSetSecurity(ModelMap model, HttpServletRequest request) {
		if(svbankService.getUseSecurity(request.getUserPrincipal().getName()).getSecurityCode()==null){
			model.addAttribute("securityPinMessage","Security Pin not yet set.");
			model.addAttribute("securityPinStatus",false);
		} else{
			model.addAttribute("securityPinMessage","Security Pin already set.");
			model.addAttribute("securityPinStatus",true);
		}
		model.addAttribute("securityHomePage", true);
		return "securityPin";
	}
	
	@RequestMapping(value = "register/registerUser", method = RequestMethod.POST)
	  public String registerUser(@ModelAttribute("userDetails") UserDetailsBean userDetails,
	    BindingResult result,ModelMap model) {
	   System.out.println("test1");
	   int redirect = svbankService.registerUser(userDetails);
	   System.out.println("redirect from dao is : "+redirect);
	   if(redirect==1)
	   {
	    model.addAttribute("accRegMessage","Request sent to bank manager for approval, You will receive a mail shortly regarding the status of your request.");  
	   }else
	   {
	    model.addAttribute("accRegMessage","Rejacte ayindi ra daffa try again.");
	   }
	   return "redirect:/login";
	  }
	
	@RequestMapping("/editprofile")
	 public String viewprofile(ModelMap model, HttpServletRequest request) {
	  String userId = request.getUserPrincipal().getName();
	  System.out.println("userId in view profile : "+userId);
	  model.addAttribute("userDetails", svbankService.getUserDetails(userId));
	  return "userProfile";
	 }
	 
	 @RequestMapping("/edituserdetails")
	 public String editprofile(@ModelAttribute("userDetails") UserDetails userDetails,
	      BindingResult result,ModelMap model) {
	  System.out.println("userId in edit profile ra areyyy : "+userDetails.getUserId());
	  System.out.println("dob in edit profile ra areyyy : "+userDetails.getDob());
	  //model.addAttribute("userUpdateStatus", svbankService.updateUserDetails(userDetails));
	  int userUpdateStatus = svbankService.updateUserDetails(userDetails);
	  //model.put("userUpdateStatus", userUpdateStatus);
	  model.addAttribute("userUpdateStatus", userUpdateStatus);
	  return "redirect:/home";
	 }
}
