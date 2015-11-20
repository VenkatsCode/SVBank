package com.svbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MortgageController {
	
	@RequestMapping(value = "/mortgageCalculator", method = RequestMethod.GET)
	public String mortgageCalculator(ModelMap map) {
		return "mortgage/mortgageCalculator";
	}
	
	@RequestMapping(value = "/mortgageRates", method = RequestMethod.GET)
	public String mortgageRates(ModelMap map) {
		return "mortgage/mortgageRates";
	}

}
