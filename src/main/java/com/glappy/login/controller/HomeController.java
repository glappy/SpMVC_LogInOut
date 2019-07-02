package com.glappy.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glappy.login.service.BBSService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {
	
	@Autowired
	BBSService bService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		log.debug("HOME Controller");
		model.addAttribute("BBS_LIST", bService.selectAll());
		model.addAttribute("BODY", "BBS_LIST");	
		return "home";
	}

}