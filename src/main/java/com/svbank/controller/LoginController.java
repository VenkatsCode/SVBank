package com.svbank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.svbank.bean.UserDetailsBean;
import com.svbank.service.SVBankService;

@Controller
public class LoginController {
	
	@Autowired
	private SVBankService svbankService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultPage(ModelMap map, HttpServletRequest request) throws IOException {
		System.out.println(request.isUserInRole("ROLE_ADMIN"));
		 if (request.isUserInRole("ROLE_ADMIN")) {
	        return "redirect:/admin/home";
	        }else{
	        	System.out.println("user:"+request.getUserPrincipal().getName()+","+svbankService.isDefaultPwd(request.getUserPrincipal().getName()));
	        	if(svbankService.isDefaultPwd(request.getUserPrincipal().getName())){
	        		System.out.println("total questions:"+svbankService.getSecurityQuestions().size());
	        		map.addAttribute("securityQuestions",svbankService.getSecurityQuestions());
	        		map.addAttribute("setPassword", new UserDetailsBean());
	        	return "/login/setPassword";
	        	}
	        return "redirect:/home";	
	        }
	}
	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String adminHome(ModelMap map, HttpServletRequest request) {
		String username = svbankService.getUserDetails(request.getUserPrincipal().getName()).getFirstName()+" "+svbankService.getUserDetails(request.getUserPrincipal().getName()).getLastName();
		System.out.println("in admin home"+request.getUserPrincipal().getName());
		map.addAttribute("username", username);
		return "/admin/adminHome";
	}

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String userHome(ModelMap map, HttpServletRequest request) {
		String username = svbankService.getUserDetails(request.getUserPrincipal().getName()).getFirstName()+" "+svbankService.getUserDetails(request.getUserPrincipal().getName()).getLastName();
		System.out.println("in home"+request.getUserPrincipal().getName());
		List<String> notifications = new ArrayList<String>();
		if(svbankService.getUseSecurity(request.getUserPrincipal().getName()).getSecurityCode()==null){
			System.out.println("user sec is null");
			notifications.add("Security Pin not yet set. Please set it from the security menu.");
		}
		
		map.addAttribute("notifications", notifications);
		map.addAttribute("username", username);
		return "homePage";
	}
	
	@RequestMapping(value = "/setPwd", method = RequestMethod.POST)
	public String setPwd(@ModelAttribute("setPassword") UserDetailsBean userDetailsBean, ModelMap model, HttpServletRequest request) {
		userDetailsBean.setUserId(request.getUserPrincipal().getName());
		System.out.println("into set pwd with pwd : "+userDetailsBean.getPassword()+" and username : "+userDetailsBean.getUserId());
		
		int result = svbankService.updatePassword(userDetailsBean, true);
		System.out.println(result);
		
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}

}
